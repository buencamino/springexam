package com.upwork.demo.scheduler;

import com.upwork.demo.dto.StockResponseDTO;
import com.upwork.demo.service.ListenerVendorAService;
import com.upwork.demo.service.ListenerVendorBService;
import com.upwork.demo.service.StockPersistenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SyncScheduler {

    private final ListenerVendorAService listenerA;
    private final ListenerVendorBService listenerB;
    private final Path csvPath;
    private final StockPersistenceService persistenceService;

    public SyncScheduler(ListenerVendorAService listenerA,
                         ListenerVendorBService listenerB, StockPersistenceService persistenceService,
                         @Value("${vendor.csv.path}") String csvPathStr
    ) {
        this.persistenceService = persistenceService;
        this.listenerA = listenerA;
        this.listenerB = listenerB;
        this.csvPath = Paths.get(csvPathStr);
    }

    @Scheduled(fixedRateString = "${vendor.sync.rate}") // every 10 seconds
    public void syncVendorAStock() {
        List<StockResponseDTO> stocks = listenerA.fetchStock();
        System.out.println("Synced stock: " + stocks);

        updateData(stocks);
    }

    @Scheduled(fixedRateString = "${vendor.sync.rate}")
    public void syncVendorBStock() {
        try {
            if (!Files.exists(csvPath)) {
                System.out.println("Vendor B CSV not found: " + csvPath);
                return;
            }

            List<StockResponseDTO> stocks = listenerB.read(csvPath);

            updateData(stocks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateData(List<StockResponseDTO> stocks) {
        for (StockResponseDTO stock : stocks) {
            persistenceService.saveOrUpdate(
                    stock.id(),
                    stock.sku(),
                    stock.name(),
                    stock.quantity(),
                    stock.vendor()
            );
        }
    }
}