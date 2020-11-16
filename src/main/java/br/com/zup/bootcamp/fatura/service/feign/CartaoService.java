package br.com.zup.bootcamp.fatura.service.feign;

import br.com.zup.bootcamp.fatura.response.LimiteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartaoService", url = "${servicos-externos.cartoes}")
public interface CartaoService {

    @GetMapping("/api/cartoes")
    ResponseEntity<LimiteResponse> buscarCartaoPorId(@RequestParam String idCartao);
}
