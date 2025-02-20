package com.clienttest.citi_client.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.clienttest.citi_client.entities.TaxEntity;
import com.clienttest.citi_client.entities.TransactionEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TransactionClientService {

    private final WebClient webClient;
    private final Random random = new Random();

    
    public TransactionClientService(WebClient.Builder webClientBuilder, @Value("${api.url}") String apiURL ) {
        this.webClient = webClientBuilder.baseUrl(apiURL).build();
    }

    @Retry(name = "transactionRetry", fallbackMethod = "handleRetryFallback")
    @CircuitBreaker(name = "transactionCircuitBreaker", fallbackMethod = "handleCircuitBreakerFallback")
    public void sendTransaction() {
        double amount = 10 + (500 - 10) * random.nextDouble(); 

        TaxEntity taxEntity = new TaxEntity(1L, 0.1, "IVA 10%");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setDate(LocalDateTime.now().toLocalDate());
        transactionEntity.setTaxEntity(taxEntity); 
        transactionEntity.setCalculatedTax(amount * 0.1);

        log.info("Enviando transacción de ${} USD a la API...", amount);

        webClient.post()
                .uri("/api/transactions")
                .bodyValue(transactionEntity)
                .retrieve()
                .bodyToMono(TransactionEntity.class) // Esperamos recibir un objeto TransactionEntity
                .doOnSuccess(response -> log.info("Transacción enviada con éxito: {}", response))
                .doOnError(error -> log.error("Error al enviar transacción", error))
                .subscribe();
    }

    // Método que inicia transacciones automáticas a intervalos regulares
    public void startAutomaticTransactions() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::sendTransaction, 0, 10, TimeUnit.SECONDS);
    }

    // Métodos de fallback para reintentos y circuit breaker
    private void handleRetryFallback(Exception e) {
        log.warn("Reintento fallido, esperando próximo intento...");
    }

    private void handleCircuitBreakerFallback(Exception e) {
        log.error("Circuit Breaker activado: la API no está respondiendo.");
    }
}
