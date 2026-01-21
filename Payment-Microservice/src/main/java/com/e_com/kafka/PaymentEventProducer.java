package com.e_com.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.e_com.entity.PaymentEntity;
import com.e_com.kafka.event.PaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentEventProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	private static final String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
	private static final String PAYMENT_FAILED = "PAYMENT_FAILED";

	public void sendPaymentEvent(PaymentEntity payment) {

		PaymentEvent event = new PaymentEvent();
		event.setPaymentId(payment.getPaymentId());
		event.setOrderId(payment.getOrderId());
		event.setUserId(payment.getUserId());
		event.setAmount(payment.getAmount());
		event.setStatus(payment.getStatus());
		event.setTransactionId(payment.getTransactionId());
		ObjectMapper mapper = new ObjectMapper();
		String msg = null;
		try {
			msg = mapper.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if ("SUCCESS".equals(payment.getStatus())) {
			kafkaTemplate.send(PAYMENT_SUCCESS, msg);
		} else {
			kafkaTemplate.send(PAYMENT_FAILED, msg);
		}
	}
}
