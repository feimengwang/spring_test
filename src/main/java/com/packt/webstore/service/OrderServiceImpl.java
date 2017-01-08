package com.packt.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ProductRepository repository;

	public void processOrder(String productId, int count) {
		Product productById = repository.getProductById(productId);
		if(productById == null){
			throw new IllegalArgumentException("");
		}
		if(productById.getUnitsInStock() <count){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock"+ productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock()-count);
	}

}
