package br.com.zup.bootcamp.fatura.response;

import br.com.zup.bootcamp.fatura.enums.StatusParcelamento;

public class ParcelamentoFaturaResponseClient {

    private StatusParcelamento resultado;

    public StatusParcelamento getResultado() {
        return resultado;
    }

    public void setResultado(StatusParcelamento resultado) {
        this.resultado = resultado;
    }
}
