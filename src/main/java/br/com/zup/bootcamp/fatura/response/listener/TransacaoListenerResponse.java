package br.com.zup.bootcamp.fatura.response.listener;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.Transacao;
import br.com.zup.bootcamp.fatura.repository.CartaoRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class TransacaoListenerResponse {

    private UUID id;
    private BigDecimal valor;
    private EstabelecimentoListenerResponse estabelecimento;
    private CartaoListenerResponse cartao;
    private String efetivadaEm;

    @Deprecated
    public TransacaoListenerResponse(){
    }

    public TransacaoListenerResponse(UUID id, BigDecimal valor, EstabelecimentoListenerResponse estabelecimento,
                                     CartaoListenerResponse cartao, String efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoListenerResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoListenerResponse getCartao() {
        return cartao;
    }

    public String getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel(CartaoRepository cartaoRepository) {
        Cartao cartao = this.cartao.toModel();

        if (cartaoRepository.findById(cartao.getId()).isEmpty()){
            cartaoRepository.save(cartao);
        }

        return new Transacao(id, valor, cartao, estabelecimento.toModel(), efetivadaEm);
    }
}