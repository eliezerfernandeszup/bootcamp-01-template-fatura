package br.com.zup.bootcamp.fatura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotNull
    private UUID idTransacao;

    @NotNull
    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    private Cartao cartao;

    @Embedded
    @NotNull
    private Estabelecimento estabelecimento;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao(){
    }

    public Transacao(@NotNull UUID idTransacao, @NotNull BigDecimal valor, Cartao cartao, Estabelecimento estabelecimento, @NotNull String efetivadaEm) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.cartao = cartao;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = converterParaLocalDate(efetivadaEm);
    }

    private LocalDateTime converterParaLocalDate(String efetivadaEm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("UTC"));
        return LocalDateTime.parse(efetivadaEm, formatter);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Integer getMesTransacaoEfetivada() {
        return efetivadaEm.getMonth().getValue();
    }

    public Integer getAnoTransacaoEfetivada() {
        return efetivadaEm.getYear();
    }

}
