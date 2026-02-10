package com.upwork.demo.repository;

import com.upwork.demo.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository
        extends JpaRepository<ProductStock, String> {
}