package com.packt.webstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	public List<Product> getProdutsByCategory(String category) {
		return productRepository.getProdutsByCategory(category);
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	public List<Product> getProductsByManufacturerAndCategory(String categaory, String manufacturer, Map<String, String> price) {
		int lowPrice = Integer.parseInt(price.get("low"));
		int heightPrice = Integer.parseInt(price.get("height"));
		List<Product> productsByCategory = productRepository.getProdutsByCategory(categaory);
		List<Product> resultProducts = new ArrayList<Product>();
		for(Product product:productsByCategory){
			if(manufacturer==null || "".equals(manufacturer) || manufacturer.equals(product.getManufacturer())){
				if(product.getUnitPrice().intValue()>lowPrice && heightPrice>product.getUnitPrice().intValue()){
					resultProducts.add(product);
				}
				
			}
		}
		
		return resultProducts;
	}

	
}
