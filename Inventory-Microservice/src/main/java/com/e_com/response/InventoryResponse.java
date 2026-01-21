package com.e_com.response;

public class InventoryResponse {

	private Long productId;
	private Integer quantity;
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InventoryResponse(Long productId, Integer quantity, String status) {
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
	}

	public InventoryResponse() {
		// TODO Auto-generated constructor stub
	}

	// getters & setters

}
