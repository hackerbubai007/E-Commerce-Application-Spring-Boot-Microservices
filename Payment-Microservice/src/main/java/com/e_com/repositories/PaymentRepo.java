package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.e_com.entity.PaymentEntity;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Long> {
}
