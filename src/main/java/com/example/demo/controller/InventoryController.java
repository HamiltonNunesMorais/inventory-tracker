package com.example.demo.controller;

import com.example.demo.dto.InventoryResponse;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint principal: POST /inventory/analyze
    @PostMapping("/analyze")
    public InventoryResponse analyzeInventory(@RequestParam("file") MultipartFile file) {
        return inventoryService.analyzeInventory(file);
    }
}
