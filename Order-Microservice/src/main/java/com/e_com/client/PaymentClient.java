package com.e_com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.e_com.client.response.PaymentRequest;
import com.e_com.client.response.PaymentResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "PAYMENT-MICROSERVICE")
public interface PaymentClient {

    @PostMapping("/payments/pay")
    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
    PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest);

    default PaymentResponse paymentFallback(PaymentRequest request, Throwable ex) {
        System.out.println("Payment service down for orderId: " + request.getOrderId());
        throw new RuntimeException("Payment Service is currently unavailable");
    }
}
