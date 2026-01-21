package com.e_com.response;

import java.time.LocalDateTime;

public class ProductResponse {

	private Long productId;
	private String productName;
	private String description;
	private Double price;
	private Integer quantity;
	private boolean isActive;

	private String categoryName;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ProductResponse(Long productId, String productName, String description, Double price, Integer quantity,
			boolean isActive, String categoryName, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.isActive = isActive;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public ProductResponse() {
		// TODO Auto-generated constructor stub
	}

}
