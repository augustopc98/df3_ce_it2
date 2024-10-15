package com.example.demo.services;

import com.example.demo.models.Discount;
import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Payment;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder Customerorder = new CustomerOrder(null, customerEmail, customerAddress, orderDate, items);
        return orderRepository.save(Customerorder);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = orderRepository.findById(orderId).orElseThrow();
        order.addOrderItem(item);
        orderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = orderRepository.findById(orderId).orElseThrow();
        order.removeOrderItem(item);
        orderRepository.save(order);
    }

    @Override
    public void applyDiscount(Long orderId, Discount discount) {
        CustomerOrder Customerorder = orderRepository.findById(orderId).orElseThrow();
        Customerorder.applyDiscount(discount);
        orderRepository.save(Customerorder);
    }

    @Override
    public void processPayment(Long orderId, Payment payment) {
        CustomerOrder Customerorder = orderRepository.findById(orderId).orElseThrow();
        payment.processPayment();
        Customerorder.getPayments().add(payment);
        orderRepository.save(Customerorder);
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder Customerorder = orderRepository.findById(orderId).orElseThrow();
        Customerorder.updateDeliveryStatus(status);
        orderRepository.save(Customerorder);
    }

    @Override
    public List<CustomerOrder> findOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }
}