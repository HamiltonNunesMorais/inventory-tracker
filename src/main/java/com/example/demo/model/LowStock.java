package com.example.demo.model;

// produto com estoque baixo
public class LowStock {
    private String productId;    // Ex: "A1"
    private String productName;  // Ex: "Widget"
    private int quantity;        // Ex: 5

    // Construtor vazio
    public LowStock() {}

    // Construtor com todos os campos
    public LowStock(String productId, String productName, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
