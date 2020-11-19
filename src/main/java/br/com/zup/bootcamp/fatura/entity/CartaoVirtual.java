package br.com.zup.bootcamp.fatura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class CartaoVirtual {

    @Id
    @GeneratedValue(generator = "uui4")
    private UUID id;

    @NotNull
    private BigDecimal limite;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @Deprecated
    public CartaoVirtual(){
    }

    public CartaoVirtual(@NotNull BigDecimal limite, Cartao cartao) {
        this.limite = limite;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }
}
