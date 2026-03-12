package com.example.demo.dto;

import com.example.demo.model.Anomaly;
import com.example.demo.model.LowStock;
import com.example.demo.model.ProductStock;

import java.util.List;

// encapsula stock, low_stock, anomalies para resposta JSON
public class InventoryResponse {
    private List<ProductStock> stock;
    private List<LowStock> lowStock;
    private List<Anomaly> anomalies;

    // Construtor vazio
    public InventoryResponse() {}

    // Construtor com todos os campos
    public InventoryResponse(List<ProductStock> stock, List<LowStock> lowStock, List<Anomaly> anomalies) {
        this.stock = stock;
        this.lowStock = lowStock;
        this.anomalies = anomalies;
    }

    // Getters e Setters
    public List<ProductStock> getStock() {
        return stock;
    }

    public void setStock(List<ProductStock> stock) {
        this.stock = stock;
    }

    public List<LowStock> getLowStock() {
        return lowStock;
    }

    public void setLowStock(List<LowStock> lowStock) {
        this.lowStock = lowStock;
    }

    public List<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
