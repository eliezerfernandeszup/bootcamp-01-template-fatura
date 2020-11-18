package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.repository.CartaoRepository;
import br.com.zup.bootcamp.fatura.request.VencimentoFaturaRequest;
import br.com.zup.bootcamp.fatura.service.feign.CartaoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/faturas")
public class VencimentoFaturaController {

    private final CartaoRepository cartaoRepository;
    private final Logger logger = LoggerFactory.getLogger(VencimentoFaturaController.class);

    public VencimentoFaturaController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{idCartao}/vencimento")
    public ResponseEntity<?> detalharFatura (@PathVariable String idCartao,
                                             @RequestBody @Valid VencimentoFaturaRequest vencimentoFaturaRequest,
                                             UriComponentsBuilder builder) {

        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);

        if (cartaoBuscado.isEmpty()){
            logger.warn("[Alteração de data da fatura]: Não foi possível encontrar o cartão.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("Não foi possível encontrar o cartão!"))
            );
        }

        Cartao cartao = cartaoBuscado.get();
        cartao.setVencimentoDaFatura(vencimentoFaturaRequest.getDia());
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
