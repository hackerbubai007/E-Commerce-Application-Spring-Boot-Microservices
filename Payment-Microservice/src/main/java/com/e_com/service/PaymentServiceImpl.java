package com.e_com.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.entity.PaymentEntity;
import com.e_com.kafka.PaymentEventProducer;
import com.e_com.repositories.PaymentRepo;
import com.e_com.request.PaymentRequest;
import com.e_com.response.PaymentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private final PaymentRepo paymentRepo;
	@Autowired
	private final PaymentEventProducer producer;

	public PaymentServiceImpl(PaymentRepo paymentRepo, PaymentEventProducer producer) {
		super();
		this.paymentRepo = paymentRepo;
		this.producer = producer;
	}

	@Override
	public PaymentResponse processPayment(PaymentRequest request) {

		PaymentEntity payment = new PaymentEntity();
		payment.setOrderId(request.getOrderId());
		payment.setUserId(request.getUserId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMode(request.getPaymentMode());
		payment.setCreatedAt(LocalDateTime.now());

		// Dummy payment gateway simulation
		boolean isSuccess = Math.random() > 0.2; // 80% success

		if (isSuccess) {
			payment.setStatus("SUCCESS");
			payment.setTransactionId(UUID.randomUUID().toString());
		} else {
			payment.setStatus("FAILED");
			payment.setTransactionId("TXN_FAILED_" + UUID.randomUUID());
		}

		PaymentEntity savedPayment = paymentRepo.save(payment);

		// Kafka Event
		
		//String paymentEventMsg=new ObjectMapper().writeValueAsString(savedPayment);
		
		producer.sendPaymentEvent(savedPayment);

		return mapToResponse(savedPayment);
	}

	private PaymentResponse mapToResponse(PaymentEntity payment) {

		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(payment.getPaymentId());
		response.setOrderId(payment.getOrderId());
		response.setStatus(payment.getStatus());
		response.setTransactionId(payment.getTransactionId());

		return response;
	}
}
