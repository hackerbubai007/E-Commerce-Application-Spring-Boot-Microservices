package com.e_com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.e_com.client.response.ProductResponse;

@FeignClient(name = "Product-Microservice")
public interface ProductClientReview {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);
}
