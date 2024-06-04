package com.lcwd.order.controllers;

import com.lcwd.order.entities.Order;
import com.lcwd.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create order
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
    }

    //get all
//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }


    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderId) {
        orderService.cancel(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order order) {
        order.setOrderId(orderId);
        return ResponseEntity.ok(orderService.update(order));
    }


    // Get orders by user ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    // Get orders by hotel ID
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Order>> getOrdersByHotelId(@PathVariable String productId) {
        return ResponseEntity.ok(orderService.getOrdersByHotelId(productId));
    }

    // Calculate order total
    @GetMapping("/{orderId}/total")
    public ResponseEntity<Double> calculateOrderTotal(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.calculateTotal(orderId));
    }
}
