package com.example.demo.services;

import com.example.demo.models.Discount;
import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Payment;

import java.util.Date;
import java.util.List;

public interface OrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    void applyDiscount(Long orderId, Discount discount);
    void processPayment(Long orderId, Payment payment);
    void updateDeliveryStatus(Long orderId, String status);
    List<CustomerOrder> findOrdersByCustomerEmail(String customerEmail);
}