package com.e_com.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.InventoryEntity;

public interface InventoryRepo extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findByProductId(Long productId);
}
