package com.e_com.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_com.client.ProductClient;
import com.e_com.client.response.ProductResponse;
import com.e_com.entity.CartEntity;
import com.e_com.entity.CartItemEntity;
import com.e_com.repositories.CartItemRepo;
import com.e_com.repositories.CartRepo;
import com.e_com.request.AddToCartRequest;
import com.e_com.request.RemoveFromCartRequest;
import com.e_com.response.CartItemResponse;
import com.e_com.response.CartResponse;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartItemRepo cartItemRepo;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final Duration CART_TTL = Duration.ofMinutes(30);

	// ---------------- ADD TO CART ----------------
	@Override
	public CartResponse addToCart(AddToCartRequest request) {

		// Validate product
		ProductResponse product = productClient.getProduct(request.getProductId());

		// Load or create cart
		CartEntity cart = cartRepo.findByUserId(request.getUserId()).orElseGet(() -> createCart(request.getUserId()));

		// Check if product already exists
		CartItemEntity item = cartItemRepo.findByCart_CartIdAndProductId(cart.getCartId(), request.getProductId())
				.orElse(null);

		if (item == null) {
			item = new CartItemEntity();
			item.setProductId(product.getProductId());
			item.setQuantity(request.getQuantity());
			item.setPriceSnapshot(product.getPrice());
			item.setCart(cart);
		} else {
			item.setQuantity(item.getQuantity() + request.getQuantity());
		}

		cartItemRepo.save(item);

		cart.setUpdatedAt(LocalDateTime.now());
		cartRepo.save(cart);

		// Reload cart with items
		CartEntity updatedCart = cartRepo.findById(cart.getCartId()).orElseThrow();

		// Cache
		redisTemplate.opsForValue().set("cart:" + request.getUserId(), updatedCart);

		return mapToResponse(updatedCart);
	}

	// ---------------- REMOVE FROM CART ----------------
	@Override
	public CartResponse removeFromCart(RemoveFromCartRequest request) {

		CartEntity cart = cartRepo.findByUserId(request.getUserId())
				.orElseThrow(() -> new RuntimeException("Cart not found"));

		CartItemEntity item = cartItemRepo.findByCart_CartIdAndProductId(cart.getCartId(), request.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not in cart"));

		cartItemRepo.delete(item);
		redisTemplate.delete("cart:" + request.getUserId());

		return mapToResponse(cart);
	}

	// ---------------- CREATE CART ----------------
	private CartEntity createCart(Long userId) {
		CartEntity cart = new CartEntity();
		cart.setUserId(userId);
		cart.setStatus("ACTIVE");
		cart.setCreatedAt(LocalDateTime.now());
		cart.setUpdatedAt(LocalDateTime.now());
		return cartRepo.save(cart);
	}

	// ---------------- MAPPER ----------------
	private CartResponse mapToResponse(CartEntity cart) {

		CartResponse response = new CartResponse();
		response.setCartId(cart.getCartId());
		response.setUserId(cart.getUserId());

		List<CartItemResponse> items = cart.getItems().stream().map(item -> {

			ProductResponse product = productClient.getProduct(item.getProductId());

			CartItemResponse itemResponse = new CartItemResponse();
			itemResponse.setProductId(item.getProductId());
			itemResponse.setProductName(product.getProductName());
			itemResponse.setQuantity(item.getQuantity());
			itemResponse.setPrice(item.getPriceSnapshot());

			return itemResponse;
		}).toList();

		response.setItems(items);
		return response;
	}
}
