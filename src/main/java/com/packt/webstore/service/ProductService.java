package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	public Product getProductById(String productID);
}
