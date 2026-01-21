package com.e_com.service;

import com.e_com.request.AddStockRequest;
import com.e_com.request.ReduceStockRequest;
import com.e_com.response.InventoryResponse;

public interface IInventoryService {

	public InventoryResponse addStock(AddStockRequest request);

	public InventoryResponse getInventory(Long productId);

	public InventoryResponse reduceStock(ReduceStockRequest request);
}
