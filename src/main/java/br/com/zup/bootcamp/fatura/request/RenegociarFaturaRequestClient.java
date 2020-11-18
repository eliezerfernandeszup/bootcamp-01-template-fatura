package br.com.zup.bootcamp.fatura.request;

import br.com.zup.bootcamp.fatura.entity.Renegociacao;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class RenegociarFaturaRequestClient {

    @NotNull
    private UUID identificadorDaFatura;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal valor;


    public RenegociarFaturaRequestClient(Renegociacao renegociacao) {
        Assert.notNull(renegociacao, "A renegociação não pode ser nula.");
        this.identificadorDaFatura = renegociacao.getFatura().getId();
        this.quantidade = renegociacao.getParcelas();
        this.valor = renegociacao.getValorDaParcela();
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
}
