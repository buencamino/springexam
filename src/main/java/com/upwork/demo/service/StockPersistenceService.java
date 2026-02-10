package com.upwork.demo.service;

import com.upwork.demo.entity.ProductStock;
import com.upwork.demo.repository.ProductStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class StockPersistenceService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(StockPersistenceService.class));

    public static void main(String[] args) {
        LOGGER.info("initializing...");
    }

    private final ProductStockRepository repository;

    public StockPersistenceService(ProductStockRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveOrUpdate(long id, String sku, String productName, int quantity, String vendor) {
        ProductStock stock = repository
                .findById(sku)
                .orElse(new ProductStock(id, sku, productName, 0, vendor));

        int previousQuantity = stock.getQuantity();
        stock.setQuantity(quantity);
        repository.save(stock);

        if (previousQuantity > 0 && quantity == 0) {
            LOGGER.warning("Product out of stock: SKU={" + sku + "}, Name={" + quantity + "}");
        }
    }

    @Transactional
    public List<ProductStock> findAll() {
        return repository.findAll();
    }
}