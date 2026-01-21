package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.request.CreateOrderRequest;
import com.e_com.response.OrderResponse;
import com.e_com.service.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
		return ResponseEntity.ok(orderService.createOrder(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrder(id));
	}
}
