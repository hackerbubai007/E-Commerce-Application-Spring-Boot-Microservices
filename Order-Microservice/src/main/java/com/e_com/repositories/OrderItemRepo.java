package com.e_com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_com.entity.OrderItemEntity;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItemEntity, Long> {

	List<OrderItemEntity> findByOrderOrderId(Long orderId);
}
