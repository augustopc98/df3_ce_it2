package com.example.demo.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerEmail;
    private String customerAddress;
    private LocalDate orderDate;
    private String deliveryStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Payment> payments = new ArrayList<>();

    // Default constructor
    public CustomerOrder(Long id, String customerEmail, String customerAddress, LocalDate orderDate, List<OrderItem> items) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.items = items;
        this.deliveryStatus = "Pending";
    }
    public CustomerOrder() {
    }

    // Parameterized constructor
    public CustomerOrder(String customerEmail, String customerAddress, LocalDate orderDate) {
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.deliveryStatus = "Pending";
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }

    // Methods for managing the order items
    public void addOrderItem(OrderItem item) {
        items.add(item);
    }

    public void removeOrderItem(OrderItem item) {
        items.remove(item);
    }

    // Method to calculate the total order price
    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Method to apply a discount to the total amount
    public void applyDiscount(Discount discount) {
        BigDecimal total = calculateTotal();
        BigDecimal discountAmount = discount.applyDiscount(total);
        System.out.println("Discount applied: " + discountAmount);
    }

    // Method to update delivery status
    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }

    // Validation method
    public boolean validateOrder() {
        return !items.isEmpty();
    }
}
