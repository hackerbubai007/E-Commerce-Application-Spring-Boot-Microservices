package com.e_com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.CartItemEntity;

public interface CartItemRepo extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByCart_CartIdAndProductId(Long cartId, Long productId);

    void deleteByCart_CartIdAndProductId(Long cartId, Long productId);
}

