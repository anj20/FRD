package com.lcwd.product.controllers;

import com.lcwd.product.entites.Product;
import com.lcwd.product.services.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Product Management System")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create
//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }


    //get single
//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> createProductById(@PathVariable String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.get(productId));
    }


    //get all
//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    // Update
//    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody Product productDetails) {
        Product updatedProduct = productService.update(productId, productDetails);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    // Delete
//    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

