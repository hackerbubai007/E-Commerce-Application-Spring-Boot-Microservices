package com.e_com.service;

import org.springframework.stereotype.Service;

import com.e_com.request.AddToCartRequest;
import com.e_com.request.RemoveFromCartRequest;
import com.e_com.response.CartResponse;
@Service
public interface ICartService {

	public CartResponse addToCart(AddToCartRequest request);

	public CartResponse removeFromCart(RemoveFromCartRequest request);
}
