package com.example.demo.model;

// estado atual de um produto
public class ProductStock {
    private String productId;    // Ex: "A1"
    private String productName;  // Ex: "Widget"
    private int quantity;        // Ex: 100

    // Construtor vazio
    public ProductStock() {}

    // Construtor com todos os campos
    public ProductStock(String productId, String productName, int quantity) {
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
