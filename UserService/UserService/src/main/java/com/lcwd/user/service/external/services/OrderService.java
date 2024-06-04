package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "ORDER-SERVICE")
public interface OrderService {


    //get

    //POST

    @PostMapping("/orders")
    public ResponseEntity<Orders> createRating(Orders values);


    //PUT
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Orders> updateRating(@PathVariable("orderId") String ratingId, Orders orders);


    @DeleteMapping("/orders/{orderId}")
    public void deleteRating(@PathVariable String orderId);
}
