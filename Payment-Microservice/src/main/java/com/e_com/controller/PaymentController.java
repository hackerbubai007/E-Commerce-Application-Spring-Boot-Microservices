package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.e_com.request.PaymentRequest;
import com.e_com.response.PaymentResponse;
import com.e_com.service.IPaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private final IPaymentService paymentService;

	public PaymentController(IPaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/pay")
	public PaymentResponse processPayment(@RequestBody PaymentRequest request) {

		if (request.getPaymentMode().equalsIgnoreCase("UPI")) {

			return paymentService.processPayment(request);

		} else if (request.getPaymentMode().equalsIgnoreCase("CARD")) {

			return paymentService.processPayment(request);

		} else {

			return paymentService.processPayment(request);
		}

	}
}
