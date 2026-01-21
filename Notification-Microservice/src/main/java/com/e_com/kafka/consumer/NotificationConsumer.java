package com.e_com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.e_com.kafka.event.OrderEvent;
import com.e_com.kafka.event.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationConsumer {

    private final ObjectMapper mapper = new ObjectMapper();

    // ================= ORDER CREATED =================

    @KafkaListener(topics = "ORDER_CREATED", groupId = "notification-group")
    public void onOrderCreated(String msg) throws Exception {

        // Handle double-encoded JSON safely
        if (msg.startsWith("\"")) {
            msg = mapper.readValue(msg, String.class);
        }

        OrderEvent event = mapper.readValue(msg, OrderEvent.class);

        System.out.println(" ORDER CREATED Notification");
        System.out.println("OrderId: " + event.getOrderId());
        System.out.println("UserId: " + event.getUserId());
        System.out.println("Amount: " + event.getTotalAmount());

        sendEmail(event.getUserId(), "Your order has been created successfully!");
        sendSms(event.getUserId(), "Order placed. OrderId: " + event.getOrderId());
    }

    // ================= PAYMENT SUCCESS =================

    @KafkaListener(topics = "PAYMENT_SUCCESS", groupId = "notification-group")
    public void onPaymentSuccess(String msg) throws Exception {

        // Handle double-encoded JSON safely
        if (msg.startsWith("\"")) {
            msg = mapper.readValue(msg, String.class);
        }

        PaymentEvent event = mapper.readValue(msg, PaymentEvent.class);

        System.out.println(" PAYMENT SUCCESS Notification");
        System.out.println("OrderId: " + event.getOrderId());
        System.out.println("TransactionId: " + event.getTransactionId());

        sendEmail(event.getUserId(), "Payment successful for OrderId: " + event.getOrderId());
        sendSms(event.getUserId(), "Payment successful. TxnId: " + event.getTransactionId());
    }

    // ================= Notifiers =================

    private void sendEmail(Long userId, String message) {
        System.out.println(" Email sent to user " + userId + " : " + message);
    }

    private void sendSms(Long userId, String message) {
        System.out.println(" SMS sent to user " + userId + " : " + message);
    }
}
