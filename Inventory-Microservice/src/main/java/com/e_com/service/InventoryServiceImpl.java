package com.e_com.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.client.ProductClient;
import com.e_com.entity.InventoryEntity;
import com.e_com.repositories.InventoryRepo;
import com.e_com.request.AddStockRequest;
import com.e_com.request.ReduceStockRequest;
import com.e_com.response.InventoryResponse;

@Service
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;

	@Autowired
	private ProductClient productClient; // Feign client to Product Service

	// ---------------- ADD STOCK ----------------
	@Override
	public InventoryResponse addStock(AddStockRequest request) {

		// Validate product exists
		productClient.getProductById(request.getProductId());

		InventoryEntity inventory = inventoryRepo.findByProductId(request.getProductId()).orElse(new InventoryEntity());

		inventory.setProductId(request.getProductId());
		inventory.setQuantity(inventory.getQuantity() == null ? request.getQuantity()
				: inventory.getQuantity() + request.getQuantity());
		inventory.setLastUpdated(LocalDateTime.now());

		InventoryEntity saved = inventoryRepo.save(inventory);

		return buildResponse(saved, "STOCK_ADDED");
	}

	// ---------------- GET STOCK ----------------
	@Override
	public InventoryResponse getInventory(Long productId) {

		InventoryEntity inventory = inventoryRepo.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found in inventory"));

		return buildResponse(inventory, "AVAILABLE");
	}

	// ---------------- REDUCE STOCK ----------------
	@Override
	public InventoryResponse reduceStock(ReduceStockRequest request) {

		InventoryEntity inventory = inventoryRepo.findByProductId(request.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found in inventory"));

		if (inventory.getQuantity() < request.getQuantity()) {
			throw new RuntimeException("Insufficient stock");
		}

		inventory.setQuantity(inventory.getQuantity() - request.getQuantity());
		inventory.setLastUpdated(LocalDateTime.now());

		InventoryEntity saved = inventoryRepo.save(inventory);

		return buildResponse(saved, "STOCK_REDUCED");
	}

	// ---------------- RESPONSE BUILDER ----------------
	private InventoryResponse buildResponse(InventoryEntity inventory, String status) {
		InventoryResponse response = new InventoryResponse();
		response.setProductId(inventory.getProductId());
		response.setQuantity(inventory.getQuantity());
		response.setStatus(status);
		return response;
	}
}
