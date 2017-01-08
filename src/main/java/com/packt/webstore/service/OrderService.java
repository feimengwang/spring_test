package com.packt.webstore.service;

public interface OrderService {
	void processOrder(String productId, int count);
}
