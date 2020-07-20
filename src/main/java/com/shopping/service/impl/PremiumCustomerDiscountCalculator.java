package com.shopping.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CustomerDao;
import com.shopping.model.Customer;
import com.shopping.service.DiscountCalculator;

@Service
public class PremiumCustomerDiscountCalculator implements DiscountCalculator {
	
	@Autowired
	CustomerDao customerDao;
	
	public static final int SLAB1_DISCOUNT = 10;
	
	public static final int SLAB2_DISCOUNT = 15;
	
	public static final int SLAB3_DISCOUNT = 20;
	
	public static final int SLAB4_DISCOUNT = 30;
	
	public static final int SLAB1_DEDUCTION = 0;
	
	public static final int SLAB2_DEDUCTION = 4000;
	
	public static final int SLAB3_DEDUCTION = 8000;
	
	public static final int SLAB4_DEDUCTION = 12000;
	
	@Override
	public BigDecimal calculate(int customerId) {
		
		Customer customer = customerDao.getRegularCustomersById(customerId);
		
		double  discountedPrice = 0.0;
		
		double purchasedAmount = customer.getPurchasedAmount().doubleValue();

		//Purchased amount is greater than $12000 i.e. $12001 & above
		if(purchasedAmount > SLAB4_DEDUCTION) {
			discountedPrice += DiscountCalculator.discountCalculate(purchasedAmount,SLAB4_DEDUCTION,SLAB4_DISCOUNT);
			purchasedAmount = purchasedAmount - SLAB3_DEDUCTION;
		} 
		
		//Purchased amount is from 8001 to 12000
		if(purchasedAmount > SLAB3_DEDUCTION && purchasedAmount <= SLAB4_DEDUCTION) {
			discountedPrice += DiscountCalculator.discountCalculate(purchasedAmount,SLAB3_DEDUCTION,SLAB3_DISCOUNT);
			purchasedAmount = purchasedAmount - SLAB2_DEDUCTION;
		} 
		
		//Purchased amount is from 4001 to 8000
		if(purchasedAmount > SLAB2_DEDUCTION && purchasedAmount <= SLAB3_DEDUCTION) {
			discountedPrice += DiscountCalculator.discountCalculate(purchasedAmount,SLAB2_DEDUCTION,SLAB2_DISCOUNT);
			purchasedAmount = purchasedAmount - SLAB2_DEDUCTION;
		} 
		
		//Purchased amount is from 0 to 4000
		discountedPrice += purchasedAmount <=SLAB2_DEDUCTION ? DiscountCalculator.discountCalculate(purchasedAmount,SLAB1_DEDUCTION,SLAB1_DISCOUNT) : 0;

		
		customer.setDiscountPrice(BigDecimal.valueOf(discountedPrice));
		customer.setFinalAmount(customer.getPurchasedAmount().subtract(customer.getDiscountPrice()));
		return customer.getFinalAmount();
	}

}
