package com.packt.webstore.domain;

import java.io.Serializable;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8589577749287770870L;
	private String customerId;
	private String name;
	private Address billingAddress;
	private String phoneNumber;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Customer() {
		super();
		this.billingAddress = new Address();
	}

	public Customer(String customerId, String name) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.billingAddress = new Address();
	}

}
