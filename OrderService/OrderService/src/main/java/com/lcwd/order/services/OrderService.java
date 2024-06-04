package com.lcwd.order.services;

import com.lcwd.order.entities.Order;

import java.util.List;


public interface OrderService {

    // Create order
    Order create(Order order);

    // Update order
    Order update(Order order);

    // Cancel order
    void cancel(String orderId);

    // Get all orders
    List<Order> getOrders();

    // Get orders by user ID
    List<Order> getOrdersByUserId(String userId);

    // Get orders by hotel ID
    List<Order> getOrdersByHotelId(String hotelId);


    // Calculate order total
    Double calculateTotal(String orderId);


}
