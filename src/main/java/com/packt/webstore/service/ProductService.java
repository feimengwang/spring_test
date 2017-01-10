package com.packt.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	public Product getProductById(String productID);
	public List<Product> getProdutsByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>>	filterParams);
	
	public List<Product> getProductsByManufacturerAndCategory(String categaory,String manufacturer,Map<String,String> price);
}
