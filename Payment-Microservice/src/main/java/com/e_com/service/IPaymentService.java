package com.e_com.service;

import com.e_com.request.PaymentRequest;
import com.e_com.response.PaymentResponse;

public interface IPaymentService {

	PaymentResponse processPayment(PaymentRequest request);
}
