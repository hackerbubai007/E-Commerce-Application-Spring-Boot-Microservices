package com.e_com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.e_com.inventory.response.InventoryResponse;

@FeignClient(name = "INVENTORY-MICROSERVICE")
public interface InventoryClient {

    @GetMapping("/inventory/{productId}")
    InventoryResponse getStock(@PathVariable("productId") Long productId);
}
