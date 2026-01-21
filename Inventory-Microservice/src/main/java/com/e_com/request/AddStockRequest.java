package com.e_com.request;

public class AddStockRequest {

	private Long productId;
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public AddStockRequest(Long productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public AddStockRequest() {
		// TODO Auto-generated constructor stub
	}

	// getters & setters

}
