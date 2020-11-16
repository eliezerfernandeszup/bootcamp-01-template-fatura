package br.com.zup.bootcamp.fatura.entity;

import br.com.zup.bootcamp.fatura.response.EstabelecimentoResponse;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Estabelecimento {

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    @Deprecated
    public Estabelecimento(){
    }

    public Estabelecimento(@NotBlank String nome, @NotBlank String cidade, @NotBlank String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public EstabelecimentoResponse toResponse() {
        return new EstabelecimentoResponse(this.nome, this.cidade, this.endereco);
    }
}
