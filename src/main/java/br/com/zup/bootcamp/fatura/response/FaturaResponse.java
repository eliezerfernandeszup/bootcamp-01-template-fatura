package br.com.zup.bootcamp.fatura.response;

import java.math.BigDecimal;
import java.util.Set;

public class FaturaResponse {

    private Integer mes;
    private Integer ano;
    private BigDecimal totalFatura;
    private Set<TransacaoResponse> transacoes;


    public FaturaResponse(Integer mes, Integer ano, Set<TransacaoResponse> transacoes, BigDecimal totalFatura) {
        this.mes = mes;
        this.ano = ano;
        this.totalFatura = totalFatura;
        this.transacoes = transacoes;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }

    public Set<TransacaoResponse> getTransacoes() {
        return transacoes;
    }

    public BigDecimal getTotalFatura() {
        return totalFatura;
    }
}
