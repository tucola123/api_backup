package com.enlatados.api;

import com.enlatados.api.modelo.Lista;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public Lista listaRepartidores() {
		return new Lista("listaRepartidores");
	}

	@Bean
	public Lista listaVehiculos() {
		return new Lista("listaVehiculos");
	}

}
