package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.response.SaldoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoVirtualService {

    private final ConsultarSaldoService consultarSaldoService;

    public CartaoVirtualService(ConsultarSaldoService consultarSaldoService) {
        this.consultarSaldoService = consultarSaldoService;
    }

    public boolean verificarSeLimiteDoCartaoEstaDisponivel (String idCartao, BigDecimal limiteRequisitado) {

        SaldoResponse saldoResponse = consultarSaldoService.processarValorDoSaldo(idCartao);

        return saldoResponse.getSaldo().compareTo(limiteRequisitado) > 0;
    }
}
