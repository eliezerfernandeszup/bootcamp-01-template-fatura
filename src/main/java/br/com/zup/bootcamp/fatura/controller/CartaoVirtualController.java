package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.CartaoVirtual;
import br.com.zup.bootcamp.fatura.repository.CartaoRepository;
import br.com.zup.bootcamp.fatura.request.CartaoVirtualRequest;
import br.com.zup.bootcamp.fatura.service.CartaoVirtualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/cartoes")
public class CartaoVirtualController {

    private final CartaoVirtualService cartaoVirtualService;
    private final CartaoRepository cartaoRepository;
    private final EntityManager entityManager;

    public CartaoVirtualController(CartaoVirtualService cartaoVirtualService, CartaoRepository cartaoRepository, EntityManager entityManager) {
        this.cartaoVirtualService = cartaoVirtualService;
        this.cartaoRepository = cartaoRepository;
        this.entityManager = entityManager;
    }

    @PostMapping("/{idCartao}/cartao-virtual")
    public ResponseEntity<?> criarCartaoVirtual (@PathVariable String idCartao,
                                                 @RequestBody @Valid CartaoVirtualRequest cartaoVirtualRequest,
                                                 UriComponentsBuilder uri) {

        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);

        if (cartaoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("O Cartão não foi encontrado!"))
            );
        }

        Cartao cartao = cartaoBuscado.get();

        if(!cartaoVirtualService.verificarSeLimiteDoCartaoEstaDisponivel(cartao, cartaoVirtualRequest.getLimite())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ErroPadronizado(Collections.singleton("O Limite não estão disponível para o valor socilitado!"))
            );
        }

        CartaoVirtual cartaoVirtual = cartaoVirtualRequest.toCartao(cartao);
        entityManager.persist(cartaoVirtual);

        return ResponseEntity
                .created(uri.path("/api/cartoes/{idCartao}/cartao-virtual/{IdCartaoVitual}")
                        .buildAndExpand(cartao.getId(), cartaoVirtual.getId())
                        .toUri())
                .build();
    }
}
