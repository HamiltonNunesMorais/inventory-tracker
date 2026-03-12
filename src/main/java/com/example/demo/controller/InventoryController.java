package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dto.InventoryResponse;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.demo.exception.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryService inventoryService;

    // Endpoint principal: POST /inventory/analyze
    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeInventory(@RequestParam(value = "file", required = true) MultipartFile file) {
        if (file == null || file.isEmpty()) {
            logger.warn("Requisição recebida sem arquivo ou arquivo vazio - Controller message");
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse(400, "Arquivo é obrigatório e não pode estar vazio"));
        }

        logger.info("Recebida requisição de análise para arquivo: {}", file.getOriginalFilename());
        InventoryResponse response = inventoryService.analyzeInventory(file);

        logger.info("Análise concluída. Retornando resposta com {} produto(s), {} low stock e {} anomalia(s)",
                response.getStock().size(),
                response.getLowStock().size(),
                response.getAnomalies().size());

        return ResponseEntity.ok(response);
    }

}
