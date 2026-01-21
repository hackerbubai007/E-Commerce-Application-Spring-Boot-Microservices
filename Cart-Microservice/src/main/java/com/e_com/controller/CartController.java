package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.e_com.request.AddToCartRequest;
import com.e_com.request.RemoveFromCartRequest;
import com.e_com.response.CartResponse;
import com.e_com.service.ICartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    // Add product to cart
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@Valid @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(request));
    }

    // Remove product from cart
    @DeleteMapping("/remove")
    public ResponseEntity<CartResponse> removeFromCart(@Valid @RequestBody RemoveFromCartRequest request) {
        return ResponseEntity.ok(cartService.removeFromCart(request));
    }
}
