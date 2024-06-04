package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {

    @GetMapping("/products/{productId}")
    Products getHotel(@PathVariable("productId") String productId);

   
}
