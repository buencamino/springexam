package com.upwork.demo.controller;

import com.upwork.demo.entity.ProductStock;
import com.upwork.demo.repository.ProductStockRepository;
import com.upwork.demo.service.StockPersistenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class RetrieveAllController {
    private final StockPersistenceService stockService;

    public RetrieveAllController(StockPersistenceService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping
    public List<ProductStock> findAll() {
        return stockService.findAll();
    }
}
