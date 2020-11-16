package br.com.zup.bootcamp.fatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaturaApplication.class, args);
	}

}
