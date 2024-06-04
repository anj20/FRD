package com.lcwd.user.service;

import com.lcwd.user.service.external.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private OrderService orderService;

  //  @Test
//    void createRating() {
//        Orders order = Orders.builder().order(10).userId("").hotelId("").feedback("this is created using feign client").build();
////        ResponseEntity<Orders> ratingResponseEntity = ratingService.createRating(order);
//        System.out.println("new order created");
//    }


}
