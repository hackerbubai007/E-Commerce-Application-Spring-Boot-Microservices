package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.request.AddStockRequest;
import com.e_com.request.ReduceStockRequest;
import com.e_com.response.InventoryResponse;
import com.e_com.service.IInventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;

	@PostMapping("/add")
	public InventoryResponse addStock(@RequestBody AddStockRequest request) {
		return inventoryService.addStock(request);
	}

	@GetMapping("/{productId}")
	public InventoryResponse getInventory(@PathVariable Long productId) {
		return inventoryService.getInventory(productId);
	}

	@PutMapping("/reduce")
	public InventoryResponse reduceStock(@RequestBody ReduceStockRequest request) {
		return inventoryService.reduceStock(request);
	}
}
