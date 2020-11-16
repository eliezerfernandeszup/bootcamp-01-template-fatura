package br.com.zup.bootcamp.fatura.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(BigDecimal valor, EstabelecimentoResponse estabelecimento, LocalDateTime efetivadaEm) {
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
