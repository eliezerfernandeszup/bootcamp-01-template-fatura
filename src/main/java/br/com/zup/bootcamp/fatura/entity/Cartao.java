package br.com.zup.bootcamp.fatura.entity;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

    @Id
    private String id;

    @NotBlank
    private String email;

    @NotNull
    @Min(1)
    @Max(31)
    private Integer vencimentoDaFatura;

    @Deprecated
    public Cartao(){
    }

    public Cartao(@NotNull String id, @NotBlank String email) {
        this.id = id;
        this.email = email;
        this.vencimentoDaFatura = 5;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setVencimentoDaFatura(Integer vencimentoDaFatura) {
        Assert.notNull(vencimentoDaFatura, "O vencimento da fatura não pode ser nulo.");
        this.vencimentoDaFatura = vencimentoDaFatura;
    }
}
