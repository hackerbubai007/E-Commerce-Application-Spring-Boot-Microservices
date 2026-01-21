package com.e_com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	private Long productId;
	private Integer quantity;
	private Double priceSnapshot;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	private CartEntity cart;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Double getPriceSnapshot() {
		return priceSnapshot;
	}

	public void setPriceSnapshot(Double priceSnapshot) {
		this.priceSnapshot = priceSnapshot;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public CartItemEntity(Long itemId, Long productId, Integer quantity, Double priceSnapshot, CartEntity cart) {
		this.itemId = itemId;
		this.productId = productId;
		this.quantity = quantity;
		this.priceSnapshot = priceSnapshot;
		this.cart = cart;
	}

	public CartItemEntity() {
		// TODO Auto-generated constructor stub
	}

}
