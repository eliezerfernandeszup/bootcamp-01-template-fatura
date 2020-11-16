package br.com.zup.bootcamp.fatura.entity;

import br.com.zup.bootcamp.fatura.response.FaturaResponse;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Fatura {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @Positive
    @NotNull
    private Integer mes;

    @Positive
    @NotNull
    private Integer ano;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Transacao> transacoes = new HashSet<>();

    @ManyToOne
    @NotNull
    private  Cartao cartao;

    @Deprecated
    public Fatura(){
    }

    public Fatura(@Positive @NotNull Integer mes, @Positive @NotNull Integer ano, Set<Transacao> transacoes) {
        this.mes = mes;
        this.ano = ano;
        this.transacoes = transacoes;
    }

    public Fatura(@Positive @NotNull Integer mes, @Positive @NotNull Integer ano, @NotNull Cartao cartao) {
        this.mes = mes;
        this.ano = ano;
        this.cartao = cartao;
    }

    public void setTransacoes(Transacao transacao) {
        Assert.notNull(transacao, "A transação não pode ser nula");
        this.transacoes.add(transacao);
    }

    public FaturaResponse toResponse() {
        return new FaturaResponse(this.mes, this.ano, Transacao.toResponseSet(transacoes), calcularTotalFatura());
    }

    private BigDecimal calcularTotalFatura() {
        Assert.notNull(transacoes, "As transações não podem ser nulas.");
        return transacoes.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSaldoDoCartao(BigDecimal limite) {
        return limite.subtract(calcularTotalFatura()).setScale(2, RoundingMode.CEILING);
    }
}
