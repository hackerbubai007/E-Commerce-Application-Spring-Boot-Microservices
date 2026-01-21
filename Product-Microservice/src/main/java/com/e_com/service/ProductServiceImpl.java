package com.e_com.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.client.InventoryClient;
import com.e_com.entity.CategoryEntity;
import com.e_com.entity.ProductEntity;
import com.e_com.inventory.response.InventoryResponse;
import com.e_com.inventory.response.ProductWithStockResponse;
import com.e_com.repositories.CategoryRepo;
import com.e_com.repositories.ProductRepo;
import com.e_com.request.ProductRequest;
import com.e_com.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private InventoryClient inventoryClient;

	@Autowired
	private ModelMapper modelMapper;

	// ---------------- ADD PRODUCT ----------------
	@Override
	public ProductResponse addProduct(ProductRequest request) {

		CategoryEntity category = categoryRepo.findById(request.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found"));

		ProductEntity product = new ProductEntity();
		product.setProductName(request.getProductName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setCategory(category);
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());

		ProductEntity savedProduct = productRepo.save(product);

		return mapToResponse(savedProduct);
	}

	// ---------------- GET PRODUCT ----------------
	@Override
	public ProductResponse findById(Long productId) {

		ProductEntity product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		return mapToResponse(product);
	}

	// ---------------- GET PRODUCT WITH STOCK ----------------
	@CircuitBreaker(name = "inventoryServiceCB", fallbackMethod = "inventoryFallback")
	@Retry(name = "inventoryServiceRetry")
	public ProductWithStockResponse findProductWithStock(Long productId) {

		ProductEntity product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		//  Feign call
		InventoryResponse stock = inventoryClient.getStock(productId);

		ProductWithStockResponse response = new ProductWithStockResponse();
		response.setProductId(product.getProductId());
		response.setProductName(product.getProductName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setCategoryName(product.getCategory().getCategoryName());
		response.setStock(stock.getQuantity());

		return response;
	}

	// ---------------- FALLBACK ----------------
	public ProductWithStockResponse inventoryFallback(Long productId, Throwable ex) {

		ProductEntity product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		ProductWithStockResponse response = new ProductWithStockResponse();
		response.setProductId(product.getProductId());
		response.setProductName(product.getProductName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setCategoryName(product.getCategory().getCategoryName());
		response.setStock(0); // fallback stock

		return response;
	}

	// ---------------- MAPPER ----------------
	private ProductResponse mapToResponse(ProductEntity product) {
		ProductResponse response = modelMapper.map(product, ProductResponse.class);
		response.setCategoryName(product.getCategory().getCategoryName());
		return response;
	}
}
