package com.lcwd.product.services;

import com.lcwd.product.entites.Product;

import java.util.List;

public interface ProductService {

    //create

    Product create(Product product);

    //get all
    List<Product> getAll();

    //get single
    Product get(String id);

    // Update an existing product
    Product update(String id, Product product);

    // Delete a product by ID
    void delete(String id);

}
