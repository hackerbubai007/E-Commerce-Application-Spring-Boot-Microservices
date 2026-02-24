package com.e_com.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.e_com.kafka.event.OrderCreatedEvent;
import com.e_com.kafka.event.OrderPaidEvent;
import com.e_com.kafka.event.OrderPaymentFailedEvent;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.order-created}")
    private String orderCreatedTopic;

    @Value("${kafka.topic.order-paid}")
    private String orderPaidTopic;

    @Value("${kafka.topic.order-payment-failed}")
    private String orderPaymentFailedTopic;

    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(orderCreatedTopic, event);
        System.out.println("ORDER_CREATED sent: " + event);
    }

    public void sendOrderPaidEvent(OrderPaidEvent event) {
        kafkaTemplate.send(orderPaidTopic, event);
        System.out.println("ORDER_PAID sent: " + event);
    }

    public void sendOrderPaymentFailedEvent(OrderPaymentFailedEvent event) {
        kafkaTemplate.send(orderPaymentFailedTopic, event);
        System.out.println("ORDER_PAYMENT_FAILED sent: " + event);
    }
}
