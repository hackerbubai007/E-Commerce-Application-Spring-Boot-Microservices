package com.e_com.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.order-created}")
    private String orderCreatedTopic;

    @Value("${kafka.topic.order-paid}")
    private String orderPaidTopic;

    @Value("${kafka.topic.order-payment-failed}")
    private String orderPaymentFailedTopic;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(String msg) {
        kafkaTemplate.send(orderCreatedTopic, msg);
        System.out.println("ORDER_CREATED sent: " + msg);
    }

    public void sendOrderPaidEvent(String msg) {
        kafkaTemplate.send(orderPaidTopic, msg);
        System.out.println("ORDER_PAYMENT DONE: " + msg);
    }

    public void sendOrderPaymentFailedEvent(String msg) {
        kafkaTemplate.send(orderPaymentFailedTopic, msg);
    }
}
