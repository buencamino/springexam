package com.upwork.demo.dto;

public record StockResponseDTO(
        long id,
        String sku,
        String name,
        int quantity,
        String vendor
) {}
