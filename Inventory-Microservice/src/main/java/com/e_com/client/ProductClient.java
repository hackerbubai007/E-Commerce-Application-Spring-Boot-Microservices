package com.e_com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.e_com.product.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "PRODUCT-MICROSERVICE")
public interface ProductClient {

    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "productServiceCB", fallbackMethod = "productFallback")
    @Retry(name = "productServiceRetry")
    ProductResponse getProductById(@PathVariable("id") Long productId);

    default ProductResponse productFallback(Long productId, Throwable ex) {
        throw new RuntimeException("Product Service is unavailable. Please try later.");
    }
}
