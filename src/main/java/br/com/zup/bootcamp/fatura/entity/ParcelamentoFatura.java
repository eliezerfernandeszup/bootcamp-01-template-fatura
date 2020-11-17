package br.com.zup.bootcamp.fatura.entity;

import br.com.zup.bootcamp.fatura.enums.StatusParcelamento;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ParcelamentoFatura {

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
    private Fatura fatura;

    @Enumerated(EnumType.STRING)
    private StatusParcelamento statusParcelamento;

    public ParcelamentoFatura(@NotNull @Positive Integer parcelas, @NotNull @Positive BigDecimal valorDaParcela) {
        this.parcelas = parcelas;
        this.valorDaParcela = valorDaParcela;
        this.statusParcelamento = StatusParcelamento.PENDENTE;
    }

    public UUID getId() {
        return id;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public StatusParcelamento getStatusParcelamento() {
        return statusParcelamento;
    }

    public void setStatusParcelamento(StatusParcelamento statusParcelamento) {
        Assert.notNull(statusParcelamento, "O status do parcelamento não pode ser nulo");
        this.statusParcelamento = statusParcelamento;
    }

    public void setFatura(Fatura fatura) {
        Assert.notNull(fatura, "A fatura não pode ser nula");
        this.fatura = fatura;
    }
}
