package com.example.demo.model;

// inconsistência detectada
public class Anomaly {
    private String productId;    // Ex: "A1"
    private String productName;  // Ex: "Widget"
    private String message;      // Ex: "Stock went negative", "Invalid row skipped"

    // Construtor vazio
    public Anomaly() {}

    // Construtor com todos os campos
    public Anomaly(String productId, String productName, String message) {
        this.productId = productId;
        this.productName = productName;
        this.message = message;
    }

    // Getters e Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
