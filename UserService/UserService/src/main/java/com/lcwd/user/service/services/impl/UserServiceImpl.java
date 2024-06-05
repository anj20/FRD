package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Products;
import com.lcwd.user.service.entities.Orders;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.ProductService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    //get single user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        Orders[] ordersOfUser = restTemplate.getForObject("http://ORDER-SERVICE/orders/users/" + user.getUserId(), Orders[].class);
        logger.info("{} ", ordersOfUser);
        List<Orders> orders = Arrays.stream(ordersOfUser).toList();
        List<Orders> ordersList = orders.stream().map(order -> {
            //ResponseEntity<Products> forEntity = restTemplate.getForEntity("http://PRODUCT-SERVICE/products/"+order.getHotelId(), Products.class);
//            Products products = productService.getHotel(order.getProductId());
//            // logger.info("response status code: {} ",forEntity.getStatusCode());
//            order.setProducts(products);
            return order;
        }).collect(Collectors.toList());

        user.setOrders(ordersList);

        return user;
    }
}
