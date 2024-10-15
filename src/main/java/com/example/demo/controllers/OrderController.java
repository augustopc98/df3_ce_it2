package com.example.demo.controllers;

import com.example.demo.models.Discount;
import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Payment;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestParam String customerEmail,
                             @RequestParam String customerAddress,
                             @RequestParam Date orderDate,
                             @RequestBody List<OrderItem> items) {
        return orderService.createOrder(customerEmail, customerAddress, orderDate, items);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        orderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items")
    public void removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        orderService.removeOrderItem(orderId, item);
    }

    @PostMapping("/{orderId}/discount")
    public void applyDiscount(@PathVariable Long orderId, @RequestBody Discount discount) {
        orderService.applyDiscount(orderId, discount);
    }

    @PostMapping("/{orderId}/payments")
    public void processPayment(@PathVariable Long orderId, @RequestBody Payment payment) {
        orderService.processPayment(orderId, payment);
    }

    @PatchMapping("/{orderId}/deliveryStatus")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateDeliveryStatus(orderId, status);
    }

    @GetMapping
    public List<CustomerOrder> findOrdersByCustomerEmail(@RequestParam String customerEmail) {
        return orderService.findOrdersByCustomerEmail(customerEmail);
    }
}