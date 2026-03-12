package com.example.demo.model;

// representa cada linha do CSV
public class InventoryMovement {
    private long timestamp;      // Unix timestamp
    private String productId;    // Ex: "A1"
    private String productName;  // Ex: "Widget"
    private int quantity;        // Ex: 100
    private String type;         // "in" ou "out"

    // Construtor vazio
    public InventoryMovement() {}

    // Construtor com todos os campos
    public InventoryMovement(long timestamp, String productId, String productName, int quantity, String type) {
        this.timestamp = timestamp;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.type = type;
    }

    // Getters e Setters
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
