package com.lcwd.order.services.impl;

import com.lcwd.order.entities.Order;
import com.lcwd.order.repository.OrderRepository;
import com.lcwd.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository repository;

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order update(Order order) {
        // Assuming that the order already exists and only fields are being updated
        return repository.save(order);
    }

    @Override
    public void cancel(String orderId) {
        repository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByHotelId(String productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public Double calculateTotal(String orderId) {
        Order order = repository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getQuantity()*order.getTotalPrice();
    }
}
