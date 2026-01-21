package com.e_com.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.entity.CategoryEntity;
import com.e_com.repositories.CategoryRepo;
import com.e_com.request.CategoryRequest;
import com.e_com.response.CategoryResponse;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponse addCategory(CategoryRequest request) {

		if (categoryRepo.existsByCategoryName(request.getCategoryName())) {
			throw new RuntimeException("Category already exists");
		}

		CategoryEntity category = modelMapper.map(request, CategoryEntity.class);
		CategoryEntity savedCategory = categoryRepo.save(category);

		return modelMapper.map(savedCategory, CategoryResponse.class);
	}
}
