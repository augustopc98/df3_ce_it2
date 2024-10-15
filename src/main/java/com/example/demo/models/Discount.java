package com.example.demo.models;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal discountPercentage;

    // Constructor
    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Discount() {
    }

    // Getter and setter
    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // Method to apply discount
    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.subtract(
                totalAmount.multiply(discountPercentage).divide(BigDecimal.valueOf(100))
        );
    }
}
