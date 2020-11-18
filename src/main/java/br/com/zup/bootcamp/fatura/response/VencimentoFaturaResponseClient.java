package br.com.zup.bootcamp.fatura.response;

import br.com.zup.bootcamp.fatura.enums.ResultadoVencimentoStatus;

public class VencimentoFaturaResponseClient {

    private ResultadoVencimentoStatus resultado;

    public ResultadoVencimentoStatus getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoVencimentoStatus resultado) {
        this.resultado = resultado;
    }
}