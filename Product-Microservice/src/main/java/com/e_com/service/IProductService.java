package com.e_com.service;

import org.springframework.stereotype.Service;

import com.e_com.inventory.response.ProductWithStockResponse;
import com.e_com.request.ProductRequest;
import com.e_com.response.ProductResponse;

@Service
public interface IProductService {
	
	ProductResponse addProduct(ProductRequest request);

	ProductResponse findById(Long productId);
	public ProductWithStockResponse findProductWithStock(Long productId);
}
