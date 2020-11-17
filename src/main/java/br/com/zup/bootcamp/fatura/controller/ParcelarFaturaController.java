package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;
import br.com.zup.bootcamp.fatura.repository.ParcelarFaturaRepository;
import br.com.zup.bootcamp.fatura.repository.FaturaRepository;
import br.com.zup.bootcamp.fatura.request.ParcelamentoFaturaRequest;
import br.com.zup.bootcamp.fatura.service.ParcelarFaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/faturas")
public class ParcelarFaturaController {

    private final FaturaRepository faturaRepository;
    private final ParcelarFaturaService parcelarFaturaService;
    private final Logger logger = LoggerFactory.getLogger(ParcelarFaturaController.class);

    public ParcelarFaturaController(FaturaRepository faturaRepository, ParcelarFaturaService parcelarFaturaService) {
        this.faturaRepository = faturaRepository;
        this.parcelarFaturaService = parcelarFaturaService;
    }

    @PostMapping("/{idCartao}/fatura/{idFatura}/parcelar")
    public ResponseEntity<?> detalharFatura (@PathVariable String idCartao, @PathVariable UUID idFatura,
                                             @RequestBody @Valid ParcelamentoFaturaRequest request,
                                             UriComponentsBuilder builder) {

        Optional<Fatura> faturaBuscada = faturaRepository.findById(idFatura);

        if (faturaBuscada.isEmpty()){
            logger.warn("[Parcelamento da fatura]: Não foi possível encontrar uma fatura para o cartão informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("Nenhuma fatura foi encontrada para o período atual do cartão!"))
            );
        }

        Fatura fatura =  faturaBuscada.get();

        var resultadoParcelamento = parcelarFaturaService.processarParcelamentoDaFatura(fatura, request);

        return ResponseEntity
                .created(builder.path("/cartoes/{id}/parcelamentos")
                        .buildAndExpand(resultadoParcelamento.getId())
                        .toUri())
                .build();
    }
}
