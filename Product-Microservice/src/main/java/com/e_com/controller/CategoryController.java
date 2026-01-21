package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.request.CategoryRequest;
import com.e_com.response.CategoryResponse;
import com.e_com.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService service;

	@PostMapping
	public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest request) {
		return ResponseEntity.ok(service.addCategory(request));
	}
}
