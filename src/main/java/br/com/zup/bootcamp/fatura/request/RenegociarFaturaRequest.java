package br.com.zup.bootcamp.fatura.request;

import br.com.zup.bootcamp.fatura.entity.Renegociacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RenegociarFaturaRequest {

    @NotNull
    @Positive
    private Integer parcelas;

    @NotNull
    @Positive
    private BigDecimal valorDaParcela;

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public void setValorDaParcela(BigDecimal valorDaParcela) {
        this.valorDaParcela = valorDaParcela;
    }

    public Renegociacao toRenegociacao() {
        return new Renegociacao(parcelas, valorDaParcela);
    }
}
