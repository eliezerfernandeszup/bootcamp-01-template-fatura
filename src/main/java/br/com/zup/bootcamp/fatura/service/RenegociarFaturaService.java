package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Renegociacao;
import br.com.zup.bootcamp.fatura.enums.ResultadoRenegociacaoStatus;
import br.com.zup.bootcamp.fatura.repository.RenegociacaoFaturaRepository;
import br.com.zup.bootcamp.fatura.request.RenegociarFaturaRequestClient;
import br.com.zup.bootcamp.fatura.service.feign.CartaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RenegociarFaturaService {

    private final Logger logger = LoggerFactory.getLogger(RenegociarFaturaService.class);
    private final CartaoClient cartaoClient;
    private final RenegociacaoFaturaRepository renegociacaoFaturaRepository;

    public RenegociarFaturaService(CartaoClient cartaoClient, RenegociacaoFaturaRepository renegociacaoFaturaRepository) {
        this.cartaoClient = cartaoClient;
        this.renegociacaoFaturaRepository = renegociacaoFaturaRepository;
    }

    public Renegociacao processarRenegociacaoDaFatura(String cartaoId, Renegociacao renegociacao) {

        Assert.notNull(renegociacao, "A Renegociação não pode ser nula para notificar o sistema legado");
        logger.info("[Renegociação da fatura]: Notificação para o sistema legado da renegociação {}", renegociacao.getId());

        RenegociarFaturaRequestClient renegociarFaturaRequestClient = new RenegociarFaturaRequestClient(renegociacao);

        try {
            var statusRenegociacao = cartaoClient.renegociarFatura(cartaoId, renegociarFaturaRequestClient);

            renegociacao.setStatusRenegociacao(statusRenegociacao.getResultado());
            logger.info("[Renegociação da fatura]: notificando sistema legado da renegociação - status {}", statusRenegociacao.getResultado());

        }catch (FeignException exception) {
            renegociacao.setStatusRenegociacao(ResultadoRenegociacaoStatus.NEGADO);
            logger.warn("[Renegociação da fatura]: Ocorreu um erro na chamada do serviço para renegociar a fatura - error: {}", exception.contentUTF8());
        }

        return renegociacaoFaturaRepository.save(renegociacao);
    }
}
