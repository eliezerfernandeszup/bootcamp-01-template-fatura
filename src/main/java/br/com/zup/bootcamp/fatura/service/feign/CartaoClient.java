package br.com.zup.bootcamp.fatura.service.feign;

import br.com.zup.bootcamp.fatura.response.LimiteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartoesCliente", url = "${servicos-externos.cartoes}",  decode404 = true)
public interface CartaoClient {

    @GetMapping("/api/cartoes/{idCartao}")
    LimiteResponse buscarLimiteDoCartao (@PathVariable String idCartao);
}
