package br.com.zup.bootcamp.fatura.request;

import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParcelamentoFaturaRequest {

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

    public ParcelamentoFatura toParcelamentoFatura() {
        return new ParcelamentoFatura(this.parcelas, this.valorDaParcela);
    }
}
