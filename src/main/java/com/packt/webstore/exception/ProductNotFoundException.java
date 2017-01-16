package com.packt.webstore.exception;

public class ProductNotFoundException extends RuntimeException {
	private String productId;

	public ProductNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductNotFoundException(String productId) {
		super();
		this.productId = productId;
	}

}
