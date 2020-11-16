package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.repository.CartaoRepository;
import br.com.zup.bootcamp.fatura.repository.FaturaRepository;
import br.com.zup.bootcamp.fatura.response.FaturaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/faturas")
public class ConsultaFaturaController {

    private final FaturaRepository faturaRepository;
    private final CartaoRepository cartaoRepository;

    public ConsultaFaturaController(FaturaRepository faturaRepository, CartaoRepository cartaoRepository) {
        this.faturaRepository = faturaRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @GetMapping("/{idCartao}")
    public ResponseEntity<?> detalharFatura (@PathVariable String idCartao) {

        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);

        if (cartaoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("O Cartão não foi encontrado!"))
            );
        }

        LocalDate dataAtual = LocalDate.now();
        List<Fatura> fatura = faturaRepository.findByCartaoIdAndMesAndAno(idCartao,
                dataAtual.getMonthValue(),
                dataAtual.getYear());

        if (fatura.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("Nenhuma transação foi encontrada para o período atual do cartão!"))
            );
        }

        FaturaResponse faturaResponse = fatura.get(0).toResponse();

        return ResponseEntity.ok(faturaResponse);
    }
}
