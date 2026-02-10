package com.upwork.demo.controller;

import com.upwork.demo.dto.StockResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendor-a")
public class VendorAController {
    @GetMapping("/stock")
    public List<StockResponseDTO> getStock(){
        return List.of(
                new StockResponseDTO(99, "00187", "Decker Hand Gloves", 3, "ace hardware"),
                new StockResponseDTO(88, "98875", "Glorious Chain Mail", 4, "true value"),
                new StockResponseDTO(77, "26543", "Delta Metal Plate", 5, "jennifer hardware")
        );
    }
}
