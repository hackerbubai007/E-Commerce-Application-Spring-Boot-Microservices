package com.e_com.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_com.entity.OrderEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUserId(Long userId);
}

