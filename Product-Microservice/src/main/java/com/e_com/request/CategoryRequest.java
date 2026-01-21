package com.e_com.request;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {

	@NotBlank
	private String categoryName;

	private String description;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryRequest(@NotBlank String categoryName, String description) {
		this.categoryName = categoryName;
		this.description = description;
	}

	public CategoryRequest() {
		// TODO Auto-generated constructor stub
	}

}
