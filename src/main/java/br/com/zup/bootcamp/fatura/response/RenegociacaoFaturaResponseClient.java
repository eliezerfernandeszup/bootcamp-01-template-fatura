package br.com.zup.bootcamp.fatura.response;

import br.com.zup.bootcamp.fatura.enums.ResultadoRenegociacaoStatus;

public class RenegociacaoFaturaResponseClient {

    private ResultadoRenegociacaoStatus resultado;

    public ResultadoRenegociacaoStatus getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoRenegociacaoStatus resultado) {
        this.resultado = resultado;
    }
}
