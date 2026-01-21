package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
}
