package com.e_com.service;

import org.springframework.stereotype.Service;

import com.e_com.request.CategoryRequest;
import com.e_com.response.CategoryResponse;

@Service
public interface ICategoryService {
    CategoryResponse addCategory(CategoryRequest request);
}
