package br.com.zup.bootcamp.fatura.entity;

import br.com.zup.bootcamp.fatura.enums.ResultadoRenegociacaoStatus;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotNull
    @Positive
    private Integer parcelas;

    @NotNull
    @Positive
    private BigDecimal valorDaParcela;

    @OneToOne
    @NotNull
    private Fatura fatura;

    @Enumerated(EnumType.STRING)
    private ResultadoRenegociacaoStatus statusRenegociacao;

    @Deprecated
    public Renegociacao(){
    }

    public Renegociacao(@NotNull @Positive Integer parcelas, @NotNull @Positive BigDecimal valorDaParcela) {
        this.parcelas = parcelas;
        this.valorDaParcela = valorDaParcela;
        this.statusRenegociacao = ResultadoRenegociacaoStatus.PENDENTE;
    }

    public UUID getId() {
        return id;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public void setFatura(Fatura fatura) {
        Assert.notNull(fatura, "A fatura não pode ser nula.");
        this.fatura = fatura;
    }

    public void setStatusRenegociacao(ResultadoRenegociacaoStatus statusRenegociacao) {
        Assert.notNull(statusRenegociacao, "O Status da renegociação não pode ser nulo.");
        this.statusRenegociacao = statusRenegociacao;
    }
}
