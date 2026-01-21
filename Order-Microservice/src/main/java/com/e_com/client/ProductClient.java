package com.e_com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.e_com.client.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "PRODUCT-MICROSERVICE")
public interface ProductClient {

    @Retry(name = "productServiceRetry")
    @CircuitBreaker(name = "productServiceCB", fallbackMethod = "productFallback")
    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    default ProductResponse productFallback(Long id, Throwable ex) {
        System.out.println("Product service down for id: " + id);
        throw new RuntimeException("Product Service is currently unavailable");
    }
}

