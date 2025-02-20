package com.clienttest.citi_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clienttest.citi_client.services.TransactionClientService;

@SpringBootApplication
public class CitiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiClientApplication.class, args);
	}

	@Bean
	CommandLineRunner start(TransactionClientService client) {
        return args -> client.startAutomaticTransactions();
    }

}
