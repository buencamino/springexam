package com.upwork.demo.controller;

import com.upwork.demo.entity.ProductStock;
import com.upwork.demo.repository.ProductStockRepository;
import com.upwork.demo.service.StockPersistenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class RetrieveController {
    private final ProductStockRepository repository;

    public RetrieveController(ProductStockRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public List<ProductStock> findAll() {
        StockPersistenceService stockService = new StockPersistenceService(repository);
        return stockService.findAll();
    }
}
