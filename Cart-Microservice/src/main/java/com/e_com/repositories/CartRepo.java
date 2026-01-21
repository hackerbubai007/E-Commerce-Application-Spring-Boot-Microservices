package com.e_com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.CartEntity;

public interface CartRepo extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
