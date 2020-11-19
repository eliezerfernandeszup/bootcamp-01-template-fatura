package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.repository.CartaoRepository;
import br.com.zup.bootcamp.fatura.service.ConsultarSaldoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/cartoes")
public class ConsultarSaldoController {

    private final ConsultarSaldoService consultarSaldoService;
    private final CartaoRepository cartaoRepository;

    public ConsultarSaldoController(ConsultarSaldoService consultarSaldoService, CartaoRepository cartaoRepository) {
        this.consultarSaldoService = consultarSaldoService;
        this.cartaoRepository = cartaoRepository;
    }

    @GetMapping("/{idCartao}/saldo")
    public ResponseEntity<?> consultarSaldo (@PathVariable String idCartao) {

        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);

        if (cartaoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("O Cartão não foi encontrado!"))
            );
        }

        Cartao cartao = cartaoBuscado.get();

        var saldoResponse = consultarSaldoService.processarValorDoSaldo(cartao);

        return ResponseEntity.ok(saldoResponse);
    }
}
