package br.com.zup.bootcamp.fatura.request;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.CartaoVirtual;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CartaoVirtualRequest {

    @NotNull
    @Positive
    private BigDecimal limite;

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public CartaoVirtual toCartao(Cartao cartao){
        Assert.notNull(cartao, "O cartão não pode ser nulo.");
        return new CartaoVirtual(this.limite , cartao);
    }
}
