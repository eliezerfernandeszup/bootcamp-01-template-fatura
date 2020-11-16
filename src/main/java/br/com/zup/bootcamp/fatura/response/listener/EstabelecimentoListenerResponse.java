package br.com.zup.bootcamp.fatura.response.listener;

import br.com.zup.bootcamp.fatura.entity.Estabelecimento;

public class EstabelecimentoListenerResponse {

    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public EstabelecimentoListenerResponse(){
    }

    public EstabelecimentoListenerResponse(String nome, String cidade, String endereco) {
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

    public Estabelecimento toModel() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
