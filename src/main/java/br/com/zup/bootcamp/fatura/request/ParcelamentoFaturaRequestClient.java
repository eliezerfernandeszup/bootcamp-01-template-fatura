package br.com.zup.bootcamp.fatura.request;

import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class ParcelamentoFaturaRequestClient {

    @NotNull
    private UUID identificadorDaFatura;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal valor;

    public ParcelamentoFaturaRequestClient(ParcelamentoFatura parcelamentoFatura) {
        this.identificadorDaFatura = parcelamentoFatura.getFatura().getId();
        this.quantidade = parcelamentoFatura.getParcelas();
        this.valor = parcelamentoFatura.getValorDaParcela();
    }

    public UUID getIdentificadorDaFatura() {
        return identificadorDaFatura;
    }

    public void setIdentificadorDaFatura(UUID identificadorDaFatura) {
        this.identificadorDaFatura = identificadorDaFatura;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ParcelamentoFaturaRequestClient{" +
                "identificadorDaFatura=" + identificadorDaFatura +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }
}
