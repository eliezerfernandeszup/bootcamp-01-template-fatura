package br.com.zup.bootcamp.fatura.config;

import br.com.zup.bootcamp.fatura.repository.CartaoRepository;
import br.com.zup.bootcamp.fatura.repository.TransacaoRepository;
import br.com.zup.bootcamp.fatura.response.listener.TransacaoListenerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ListenerDeTransacao {

    private final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);
    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoListenerResponse eventoDeTransacao) {
        Assert.notNull(eventoDeTransacao, "A transação não pode ser nula");

        var transacao = eventoDeTransacao.toModel(cartaoRepository);
        transacaoRepository.save(transacao);
    }
}
