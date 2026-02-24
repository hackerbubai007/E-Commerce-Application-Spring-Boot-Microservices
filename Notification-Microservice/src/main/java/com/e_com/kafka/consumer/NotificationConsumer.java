package com.e_com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.e_com.kafka.event.OrderCreatedEvent;
import com.e_com.kafka.event.OrderPaidEvent;
import com.e_com.kafka.event.OrderPaymentFailedEvent;

@Service
public class NotificationConsumer {

    @KafkaListener(
        topics = "ORDER_CREATED",
        groupId = "notification-group1",
        properties = {
            "spring.json.value.default.type=com.e_com.kafka.event.OrderCreatedEvent"
        }
    )
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("ORDER CREATED: " + event.getOrderId());
        sendEmail(event.getUserId(), "Order created");
    }

    @KafkaListener(
        topics = "ORDER_PAID",
        groupId = "notification-group1",
        properties = {
            "spring.json.value.default.type=com.e_com.kafka.event.OrderPaidEvent"
        }
    )
    public void onOrderPaid(OrderPaidEvent event) {
        System.out.println("ORDER PAID: " + event.getOrderId());
        sendEmail(event.getUserId(), "Payment successful");
    }

    @KafkaListener(
        topics = "ORDER_PAYMENT_FAILED",
        groupId = "notification-group1",
        properties = {
            "spring.json.value.default.type=com.e_com.kafka.event.OrderPaymentFailedEvent"
        }
    )
    public void onPaymentFailed(OrderPaymentFailedEvent event) {
        System.out.println("PAYMENT FAILED: " + event.getOrderId());
        sendEmail(event.getOrderId(), "Payment failed: " + event.getReason());
    }

    private void sendEmail(Long userId, String msg) {
        System.out.println("Email → " + userId + " : " + msg);
    }
}
