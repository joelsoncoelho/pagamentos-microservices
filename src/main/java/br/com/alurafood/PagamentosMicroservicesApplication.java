package br.com.alurafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagamentosMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentosMicroservicesApplication.class, args);
	}

}
