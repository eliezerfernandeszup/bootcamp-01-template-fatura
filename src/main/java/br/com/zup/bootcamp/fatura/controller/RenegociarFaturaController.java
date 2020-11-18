package br.com.zup.bootcamp.fatura.controller;

import br.com.zup.bootcamp.fatura.advice.ErroPadronizado;
import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.entity.Renegociacao;
import br.com.zup.bootcamp.fatura.repository.FaturaRepository;
import br.com.zup.bootcamp.fatura.repository.RenegociacaoFaturaRepository;
import br.com.zup.bootcamp.fatura.request.RenegociarFaturaRequest;
import br.com.zup.bootcamp.fatura.service.RenegociarFaturaService;
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
public class RenegociarFaturaController {

    private final FaturaRepository faturaRepository;
    private final RenegociacaoFaturaRepository renegociacaoFaturaRepository;
    private final RenegociarFaturaService renegociarFaturaService;
    private final Logger logger = LoggerFactory.getLogger(RenegociarFaturaController.class);

    public RenegociarFaturaController(FaturaRepository faturaRepository,
                                      RenegociacaoFaturaRepository renegociacaoFaturaRepository,
                                      RenegociarFaturaService renegociarFaturaService) {
        this.faturaRepository = faturaRepository;
        this.renegociacaoFaturaRepository = renegociacaoFaturaRepository;
        this.renegociarFaturaService = renegociarFaturaService;
    }

    @PostMapping("/{idCartao}/fatura/{idFatura}/renegociar")
    public ResponseEntity<?> detalharFatura (@PathVariable String idCartao, @PathVariable UUID idFatura,
                                             @RequestBody @Valid RenegociarFaturaRequest request,
                                             UriComponentsBuilder builder) {

        Optional<Fatura> faturaBuscada = faturaRepository.findById(idFatura);

        if (faturaBuscada.isEmpty()){
            logger.warn("[Renegociar Fatura]: Não foi possível encontrar uma fatura para o cartão informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErroPadronizado(Collections.singleton("Nenhuma fatura foi encontrada!"))
            );
        }

        Fatura fatura = faturaBuscada.get();

        Renegociacao renegociacao = request.toRenegociacao();
        renegociacao.setFatura(fatura);
        renegociacaoFaturaRepository.save(renegociacao);

        Renegociacao renegociacaoProcessada = renegociarFaturaService.processarRenegociacaoDaFatura(idCartao, renegociacao);

        return ResponseEntity
                .created(builder.path("/cartoes/{id}/renegociacoes")
                        .buildAndExpand(renegociacaoProcessada.getId())
                        .toUri())
                .build();
    }
}
