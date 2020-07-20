package com.shopping.model;

import java.math.BigDecimal;

public class Customer {

	private int id;
	
	private String name;
	
	private CustomerType customerType;
	
	// All prices will be in USD
	private BigDecimal purchasedAmount;
	
	private BigDecimal discountPrice;

	private BigDecimal finalAmount;
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String name, CustomerType customerType, BigDecimal purchasedAmount) {
		super();
		this.id = id;
		this.name = name;
		this.customerType = customerType;
		this.purchasedAmount = purchasedAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public BigDecimal getPurchasedAmount() {
		return purchasedAmount;
	}

	public void setPurchasedAmount(BigDecimal purchasedAmount) {
		this.purchasedAmount = purchasedAmount;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", customerType=" + customerType + ", purchasedAmount="
				+ purchasedAmount + ", discountPrice=" + discountPrice + ", finalAmount=" + finalAmount + "]";
	}
}
