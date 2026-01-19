package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
	boolean existsByEmailId(String emailId);
}
