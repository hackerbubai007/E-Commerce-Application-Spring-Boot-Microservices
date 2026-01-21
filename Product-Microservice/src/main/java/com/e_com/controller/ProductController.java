package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.inventory.response.ProductWithStockResponse;
import com.e_com.request.ProductRequest;
import com.e_com.response.ProductResponse;
import com.e_com.service.IProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private IProductService service;

    // CREATE PRODUCT
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(service.addProduct(request));
    }

//    // GET PRODUCT BY ID
//    @GetMapping("/products/{id}")
//    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long id) {
//        return ResponseEntity.ok(service.findById(id));
//    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductWithStockResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProductWithStock(id));
    }
    
}
