
// lógica de negócio: parsing, cálculo de estoque, detecção de low stock e anomalies
package com.example.demo.service;

import com.example.demo.dto.InventoryResponse;
import com.example.demo.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class InventoryService {

    public InventoryResponse analyzeInventory(MultipartFile file) {
        List<InventoryMovement> movements = parseCsv(file);

        // Ordenar por timestamp
        movements.sort(Comparator.comparingLong(InventoryMovement::getTimestamp));

        // Calcular estoque
        Map<String, ProductStock> stockMap = calculateStock(movements);

        // Identificar estoque baixo
        List<LowStock> lowStockList = findLowStock(stockMap);

        // Detectar anomalias
        List<Anomaly> anomalies = detectAnomalies(movements, stockMap);

        // Montar resposta
        return new InventoryResponse(
                new ArrayList<>(stockMap.values()),
                lowStockList,
                anomalies
        );
    }

    private List<InventoryMovement> parseCsv(MultipartFile file) {
        List<InventoryMovement> movements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                try {
                    long timestamp = Long.parseLong(record.get("timestamp"));
                    String productId = record.get("product_id");
                    String productName = record.get("product_name");
                    String type = record.get("type");
                    int quantity = Integer.parseInt(record.get("quantity"));

                    movements.add(new InventoryMovement(timestamp, productId, productName, quantity, type));
                } catch (Exception e) {
                    // Linha inválida
                    movements.add(new InventoryMovement(0, record.get("product_id"),
                            record.get("product_name"), 0, "invalid"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movements;
    }

    private Map<String, ProductStock> calculateStock(List<InventoryMovement> movements) {
        Map<String, ProductStock> stockMap = new HashMap<>();
        for (InventoryMovement m : movements) {
            if ("invalid".equals(m.getType())) continue;

            ProductStock stock = stockMap.getOrDefault(m.getProductId(),
                    new ProductStock(m.getProductId(), m.getProductName(), 0));

            if ("in".equalsIgnoreCase(m.getType())) {
                stock.setQuantity(stock.getQuantity() + m.getQuantity());
            } else if ("out".equalsIgnoreCase(m.getType())) {
                stock.setQuantity(stock.getQuantity() - m.getQuantity());
            }
            stockMap.put(m.getProductId(), stock);
        }
        return stockMap;
    }

    private List<LowStock> findLowStock(Map<String, ProductStock> stockMap) {
        List<LowStock> lowStockList = new ArrayList<>();
        for (ProductStock stock : stockMap.values()) {
            if (stock.getQuantity() < 10) {
                lowStockList.add(new LowStock(stock.getProductId(), stock.getProductName(), stock.getQuantity()));
            }
        }
        return lowStockList;
    }

    private List<Anomaly> detectAnomalies(List<InventoryMovement> movements, Map<String, ProductStock> stockMap) {
        List<Anomaly> anomalies = new ArrayList<>();
        for (InventoryMovement m : movements) {
            if ("invalid".equals(m.getType())) {
                anomalies.add(new Anomaly(m.getProductId(), m.getProductName(), "Invalid row skipped"));
            }
        }
        for (ProductStock stock : stockMap.values()) {
            if (stock.getQuantity() < 0) {
                anomalies.add(new Anomaly(stock.getProductId(), stock.getProductName(), "Stock went negative"));
            }
        }
        return anomalies;
    }
}
