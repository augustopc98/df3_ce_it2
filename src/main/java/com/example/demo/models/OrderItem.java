package com.example.demo.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    // Default constructor
    public OrderItem() {}

    // Parameterized constructor
    public OrderItem(Long productId, int quantity, BigDecimal productPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getProductPrice() { return productPrice; }
    public void setProductPrice(BigDecimal productPrice) { this.productPrice = productPrice; }

    // Method to calculate price
    public BigDecimal getPrice() {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Validation method
    public boolean validateItem() {
        return productPrice != null && productPrice.compareTo(BigDecimal.ZERO) > 0;
    }
}
