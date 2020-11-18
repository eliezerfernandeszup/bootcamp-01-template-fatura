package br.com.zup.bootcamp.fatura.service.feign;

import br.com.zup.bootcamp.fatura.request.ParcelamentoFaturaRequestClient;
import br.com.zup.bootcamp.fatura.response.LimiteResponse;
import br.com.zup.bootcamp.fatura.response.ParcelamentoFaturaResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoesCliente", url = "${servicos-externos.cartoes}",  decode404 = true)
public interface CartaoClient {

    @GetMapping("/api/cartoes/{id}")
    LimiteResponse buscarLimiteDoCartao (@PathVariable String id);

    @PostMapping("/api/cartoes/{id}/parcelas")
    ParcelamentoFaturaResponseClient parcelarFatura (@PathVariable String id,
                                                     @RequestBody ParcelamentoFaturaRequestClient request);
}
