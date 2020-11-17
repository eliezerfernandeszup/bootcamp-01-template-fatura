package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;
import br.com.zup.bootcamp.fatura.repository.ParcelarFaturaRepository;
import br.com.zup.bootcamp.fatura.request.ParcelamentoFaturaRequest;
import br.com.zup.bootcamp.fatura.service.feign.CartaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

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

        ParcelamentoFatura parcelamentoFatura = request.toParcelamentoFatura();
        parcelamentoFatura.setFatura(fatura);
        parcelarFaturaRepository.save(parcelamentoFatura);

        return parcelamentoFatura;
    }
}
