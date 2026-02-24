package com.e_com.kafka.event;

import java.math.BigDecimal;

public class OrderCreatedEvent {

    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId, Long userId, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}
