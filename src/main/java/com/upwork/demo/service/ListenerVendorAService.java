package com.upwork.demo.service;

import com.upwork.demo.dto.StockResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListenerVendorAService {
    private static WebClient webClient = null;

    public ListenerVendorAService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }

    @Retry(name = "vendorA")
    @CircuitBreaker(name = "vendorA", fallbackMethod = "fallback")
    public static List<StockResponseDTO> fetchStock() {
        return webClient.get()
                .uri("/vendor-a/stock")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<StockResponseDTO>>() {})
                .block();
    }

    public List<StockResponseDTO> fallback(Exception ex) {
        return List.of();
    }
}
