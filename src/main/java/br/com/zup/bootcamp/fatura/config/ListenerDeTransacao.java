package br.com.zup.bootcamp.fatura.config;

import br.com.zup.bootcamp.fatura.response.listener.TransacaoListenerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ListenerDeTransacao {

    private final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoListenerResponse eventoDeTransacao) {
        Assert.notNull(eventoDeTransacao, "A transação não pode ser nula");

        logger.info(eventoDeTransacao.toString());
    }
}
