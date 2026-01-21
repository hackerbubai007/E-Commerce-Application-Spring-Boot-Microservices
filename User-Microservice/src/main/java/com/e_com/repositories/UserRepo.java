package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_com.entity.UserEntity;
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	boolean existsByEmailId(String emailId);
}
