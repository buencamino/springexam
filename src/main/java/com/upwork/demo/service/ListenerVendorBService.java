package com.upwork.demo.service;

import com.upwork.demo.dto.StockResponseDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListenerVendorBService {

    public List<StockResponseDTO> read(Path csvPath) throws IOException {
        List<StockResponseDTO> result = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String productCode = parts[0].trim();
                String productName = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());

                result.add(new StockResponseDTO(productCode, productName, quantity));
            }
        }

        return result;
    }
    /*
    private static final Path CSV_PATH =
            Paths.get("/tmp/vendor-b/stock.csv");

    public List<stockResponse> readStock() {
        try (Stream<String> lines = Files.lines(CSV_PATH)) {
            return lines
                    .skip(1) // skip header
                    .map(this::parseLine)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Vendor B CSV", e);
        }
    }

    private stockResponse parseLine(String line) {
        String[] tokens = line.split(",");
        return new stockResponse(
                tokens[0],
                tokens[1],
                Integer.parseInt(tokens[2])
        );
    }*/
}

