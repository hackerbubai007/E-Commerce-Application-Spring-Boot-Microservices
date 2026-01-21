package com.e_com.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.client.PaymentClient;
import com.e_com.client.ProductClient;
import com.e_com.client.response.PaymentRequest;
import com.e_com.client.response.PaymentResponse;
import com.e_com.client.response.ProductResponse;
import com.e_com.entity.OrderEntity;
import com.e_com.entity.OrderItemEntity;
import com.e_com.kafka.OrderEventProducer;
import com.e_com.repositories.OrderRepo;
import com.e_com.request.CreateOrderRequest;
import com.e_com.request.OrderItemRequest;
import com.e_com.response.OrderItemResponse;
import com.e_com.response.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	PaymentClient paymentClient;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderEventProducer producer;

	// ================= CREATE ORDER =================

	@Override
	public OrderResponse createOrder(CreateOrderRequest request) {

		OrderEntity order = new OrderEntity();
		order.setUserId(request.getUserId());
		order.setStatus("CREATED");
		order.setCreatedAt(LocalDateTime.now());
		order.setItems(new ArrayList<>());

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (OrderItemRequest itemReq : request.getItems()) {

			ProductResponse product = productClient.getProduct(itemReq.getProductId());

			BigDecimal price = BigDecimal.valueOf(product.getPrice());
			BigDecimal quantity = BigDecimal.valueOf(itemReq.getQuantity());
			BigDecimal itemTotal = price.multiply(quantity);

			OrderItemEntity item = new OrderItemEntity();
			item.setProductId(product.getProductId());
			item.setProductName(product.getProductName());
			item.setQuantity(itemReq.getQuantity());
			item.setPrice(product.getPrice());
			item.setTotalPrice(itemTotal.doubleValue());
			item.setOrder(order);

			totalAmount = totalAmount.add(itemTotal);
			order.getItems().add(item);
		}

		order.setTotalAmount(totalAmount.doubleValue());

		OrderEntity savedOrder = orderRepo.save(order);

		// ORDER_CREATED event
		String orderJson;
		try {
			orderJson = new ObjectMapper().writeValueAsString(savedOrder);
			producer.sendOrderCreatedEvent(orderJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ================= Payment =================

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setUserId(savedOrder.getUserId());
		paymentRequest.setOrderId(savedOrder.getOrderId());
		paymentRequest.setAmount(savedOrder.getTotalAmount());
		paymentRequest.setPaymentMode("UPI");

		PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);

		if ("SUCCESS".equals(paymentResponse.getStatus())) {
			savedOrder.setStatus("PAID");
			orderRepo.save(savedOrder);

			// ORDER_PAID event
			String orderJson1;
			try {
				orderJson1 = new ObjectMapper().writeValueAsString(savedOrder);
				producer.sendOrderPaidEvent(orderJson1);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			savedOrder.setStatus("PAYMENT_FAILED");
			orderRepo.save(savedOrder);

			// ORDER_PAYMENT_FAILED event
			try {
				String orderPaymentFail = new ObjectMapper().writeValueAsString(paymentResponse);
				producer.sendOrderPaymentFailedEvent(orderPaymentFail);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mapToResponse(savedOrder);
	}

	// ================= GET ORDER =================

	@Override
	public OrderResponse getOrder(Long orderId) {

		OrderEntity order = orderRepo.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

		return mapToResponse(order);
	}

	// ================= MAPPER =================

	private OrderResponse mapToResponse(OrderEntity order) {

		OrderResponse response = new OrderResponse();
		response.setOrderId(order.getOrderId());
		response.setUserId(order.getUserId());
		response.setStatus(order.getStatus());
		response.setTotalAmount(order.getTotalAmount());

		List<OrderItemResponse> items = order.getItems().stream().map(item -> {

			OrderItemResponse res = new OrderItemResponse();
			res.setProductId(item.getProductId());
			res.setProductName(item.getProductName());
			res.setQuantity(item.getQuantity());
			res.setPrice(item.getPrice());
			res.setTotalPrice(item.getTotalPrice());

			return res;
		}).toList();

		response.setItems(items);
		return response;
	}
}
