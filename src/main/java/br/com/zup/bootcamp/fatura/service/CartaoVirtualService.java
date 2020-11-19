package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.response.SaldoResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class CartaoVirtualService {

    private final ConsultarSaldoService consultarSaldoService;

    public CartaoVirtualService(ConsultarSaldoService consultarSaldoService) {
        this.consultarSaldoService = consultarSaldoService;
    }

    public boolean verificarSeLimiteDoCartaoEstaDisponivel (Cartao cartao, BigDecimal limiteRequisitado) {
        Assert.notNull(cartao, "Para verificar se limite do cartão está disponível, o cartão não pode ser nulo.");

        SaldoResponse saldoResponse = consultarSaldoService.processarValorDoSaldo(cartao);

        return saldoResponse.getSaldo().compareTo(limiteRequisitado) > 0;
    }
}
