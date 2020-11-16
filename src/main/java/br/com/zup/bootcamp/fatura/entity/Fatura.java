package br.com.zup.bootcamp.fatura.entity;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
}
