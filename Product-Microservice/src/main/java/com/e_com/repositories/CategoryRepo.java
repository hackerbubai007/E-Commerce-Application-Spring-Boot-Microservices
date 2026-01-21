package com.e_com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
	boolean existsByCategoryName(String categoryName);
}
