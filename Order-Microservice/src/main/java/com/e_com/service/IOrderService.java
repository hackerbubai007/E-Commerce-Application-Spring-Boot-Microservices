package com.e_com.service;

import com.e_com.request.CreateOrderRequest;
import com.e_com.response.OrderResponse;

public interface IOrderService {

	public OrderResponse createOrder(CreateOrderRequest request);
	
	public OrderResponse getOrder(Long orderId);
	
}
