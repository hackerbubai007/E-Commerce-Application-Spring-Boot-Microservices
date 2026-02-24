package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_com.entity.IdempotencyEntity;

@Repository
public interface IdempotencyRepository 
        extends JpaRepository<IdempotencyEntity, String> {

}
