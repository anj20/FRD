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
        //generate  unique userid
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
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch order of the above  user from RATING SERVICE
        //http://localhost:8083/orders/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        Orders[] ratingsOfUser = restTemplate.getForObject("http://ORDER-SERVICE/orders/users/" + user.getUserId(), Orders[].class);
        logger.info("{} ", ratingsOfUser);
        List<Orders> orders = Arrays.stream(ratingsOfUser).toList();
        List<Orders> ordersList = orders.stream().map(rating -> {
            //api call to products service to get the products
            //http://localhost:8082/products/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
            //ResponseEntity<Products> forEntity = restTemplate.getForEntity("http://PRODUCT-SERVICE/products/"+order.getHotelId(), Products.class);
            Products products = productService.getHotel(rating.getProductId());
            // logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the products to order
            rating.setProducts(products);
            //return the order
            return rating;
        }).collect(Collectors.toList());

        user.setOrders(ordersList);

        return user;
    }
}
