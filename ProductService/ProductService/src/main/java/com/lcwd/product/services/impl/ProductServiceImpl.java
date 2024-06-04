package com.lcwd.product.services.impl;

import com.lcwd.product.entites.Product;
import com.lcwd.product.exceptions.ResourceNotFoundException;
import com.lcwd.product.respositories.ProductRepository;
import com.lcwd.product.services.ProductService;
import com.lcwd.product.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisService redisService;

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
            Product body= productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product with given id not found !!"));
            redisService.set(id,body,300l);
            return body;
        }
        else {
            return resp;
        }
    }

    @Override
    public Product update(String id, Product product) {
        // Ensure the product with the given ID exists
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            // Update cache
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
        // Remove from cache
        redisService.delete(id);
    }
}
