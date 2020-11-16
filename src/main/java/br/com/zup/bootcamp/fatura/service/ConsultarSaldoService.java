package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.repository.FaturaRepository;
import br.com.zup.bootcamp.fatura.response.LimiteResponse;
import br.com.zup.bootcamp.fatura.response.SaldoResponse;
import br.com.zup.bootcamp.fatura.service.feign.CartaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultarSaldoService {

    private final CartaoClient cartaoClient;
    private final Logger logger = LoggerFactory.getLogger(ConsultarSaldoService.class);
    private final FaturaRepository faturaRepository;
    private LimiteResponse limiteResponse;

    public ConsultarSaldoService(CartaoClient cartaoClient, FaturaRepository faturaRepository) {
        this.cartaoClient = cartaoClient;
        this.faturaRepository = faturaRepository;
    }

    public SaldoResponse processarValorDoSaldo(String cartaoId){
        logger.info("[Consultado Limite do Cartão] " );

        try {
            limiteResponse = cartaoClient.buscarLimiteDoCartao(cartaoId);
        }catch (FeignException exception){
            logger.warn("[FeignException] : Não foi possível consultar o límite do cartão");
        }

        LocalDate dataAtual = LocalDate.now();

        List<Fatura> faturas = faturaRepository.findByCartaoIdAndMesAndAno(cartaoId,
                dataAtual.getMonthValue(),
                dataAtual.getYear());

        if (faturas.isEmpty()){
            return new SaldoResponse(limiteResponse.getLimite());
        }

        Fatura fatura = faturas.get(0);

        return new SaldoResponse(fatura.calcularSaldoDoCartao(limiteResponse.getLimite()));
    }
}
