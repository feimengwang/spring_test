package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.Product;

public interface ProductRepository {
	public List<Product> getAllProducts();
	public Product getProductById(String productID);

	public List<Product> getProdutsByCategory(String category);
	public Set<Product> getProductsByFilter(Map<String, List<String>>	filterParams);
}
