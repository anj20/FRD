package com.lcwd.product.services.impl;

import com.lcwd.product.entites.Product;
import com.lcwd.product.exceptions.ResourceNotFoundException;
import com.lcwd.product.respositories.ProductRepository;
import com.lcwd.product.services.ProductService;
import com.lcwd.product.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public Product create(Product product) {
        String hotelId = UUID.randomUUID().toString();
        if(product.getId()==null)product.setId(hotelId);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(String id) {
        Product resp = redisService.get(id, Product.class);
        if (resp == null) {
            logger.info("Fetched Data from SQL");
            Product body= productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product with given id not found !!"));
            redisService.set(id,body,300l);
            return body;
        }
        else {
            logger.info("Fetched Data from Redis");
            return resp;
        }
    }

    @Override
    public Product update(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            redisService.set(id, updatedProduct, 300L);
            return updatedProduct;
        } else {
            throw new ResourceNotFoundException("Product with given id not found !!");
        }
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with given id not found !!"));
        productRepository.delete(product);
        redisService.delete(id);
    }
}
