package com.e_com.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;

	@Column(unique = true, nullable = false)
	private Long productId;

	private Integer quantity;

	private LocalDateTime lastUpdated;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

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

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public InventoryEntity(Long inventoryId, Long productId, Integer quantity, LocalDateTime lastUpdated) {
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
		this.lastUpdated = lastUpdated;
	}

	public InventoryEntity() {
		// TODO Auto-generated constructor stub
	}

}
