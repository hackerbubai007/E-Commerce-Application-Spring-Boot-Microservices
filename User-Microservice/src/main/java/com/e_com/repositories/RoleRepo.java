package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleName(String roleName);
}
