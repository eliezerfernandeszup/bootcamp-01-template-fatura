package br.com.zup.bootcamp.fatura.response;

import java.math.BigDecimal;

public class LimiteResponse {

    private BigDecimal limite;

    public LimiteResponse(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
