package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;
import br.com.zup.bootcamp.fatura.enums.StatusParcelamento;
import br.com.zup.bootcamp.fatura.repository.ParcelarFaturaRepository;
import br.com.zup.bootcamp.fatura.request.ParcelamentoFaturaRequest;
import br.com.zup.bootcamp.fatura.request.ParcelamentoFaturaRequestClient;
import br.com.zup.bootcamp.fatura.service.feign.CartaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.Valid;

@Service
public class ParcelarFaturaService {

    private final Logger logger = LoggerFactory.getLogger(ParcelarFaturaService.class);
    private final ParcelarFaturaRepository parcelarFaturaRepository;
    private final CartaoClient cartaoClient;

    public ParcelarFaturaService(ParcelarFaturaRepository parcelarFaturaRepository, CartaoClient cartaoClient) {
        this.parcelarFaturaRepository = parcelarFaturaRepository;
        this.cartaoClient = cartaoClient;
    }

    public ParcelamentoFatura processarParcelamentoDaFatura(Fatura fatura, @Valid ParcelamentoFaturaRequest request) {

        Assert.notNull(request, "A fatura não pode ser nula, para notificar o sistema legado");
        logger.info("[Parcelamento da fatura]: Notificação para o sistema legado da fatura {}", fatura.getId());

        ParcelamentoFatura parcelamentoFatura = request.toParcelamentoFatura();
        parcelamentoFatura.setFatura(fatura);

        ParcelamentoFatura parcelamentoSalvo = parcelarFaturaRepository.save(parcelamentoFatura);

        var parcelamentoFaturaRequestClient = new ParcelamentoFaturaRequestClient(parcelamentoFatura);

        try {
            var statusParcelamento = cartaoClient.parcelarFatura(fatura.getCartao().getId(), parcelamentoFaturaRequestClient);

            parcelamentoSalvo.setStatusParcelamento(statusParcelamento.getResultado());
            logger.info("[Parcelamento da fatura]: notificando sistema legado do parcelamento - status {}", statusParcelamento.getResultado());

        }catch (FeignException exception) {
            parcelamentoSalvo.setStatusParcelamento(StatusParcelamento.NEGADO);
            logger.warn("[Parcelamento da fatura ]: Ocorreu um erro na chamada do serviço - error: {}", exception.contentUTF8());
        }

        parcelarFaturaRepository.save(parcelamentoSalvo);

        return parcelamentoFatura;
    }
}
