package com.packt.webstore.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private String cartId;
	private Map<String, CartItem> cartItems;
	private BigDecimal grandTotal;

	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = new BigDecimal(0);
	}

	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void addCartItem(CartItem item) {
		String productId = item.getProduct().getProductId();
		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, item);
		}
		updateGrandTotal();
	}

	public void removeCartItem(CartItem item) {
		String productId = item.getProduct().getProductId();
		cartItems.remove(productId);
		updateGrandTotal();
	}

	public void updateGrandTotal() {
		grandTotal = new BigDecimal(0);
		for (CartItem item : cartItems.values()) {
			grandTotal = grandTotal.add(item.getTotalPrice());
		}
	}
}
