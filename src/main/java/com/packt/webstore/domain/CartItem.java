package com.packt.webstore.domain;

import java.math.BigDecimal;

public class CartItem {
	Product product;
	int quantity;
	BigDecimal totalPrice;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.totalPrice = product.getUnitPrice();
	}

	public void updateTotalPrice() {
		totalPrice = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
