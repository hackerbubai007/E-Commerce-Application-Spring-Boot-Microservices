package com.e_com.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {

	@NotBlank
	private String productName;

	private String description;

	@NotNull
	@Positive
	private Double price;

	@NotNull
	private Integer quantity;

	@NotNull
	private Long categoryId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProductRequest(@NotBlank String productName, String description, @NotNull @Positive Double price,
			@NotNull Integer quantity, @NotNull Long categoryId) {
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
	}

	public ProductRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
