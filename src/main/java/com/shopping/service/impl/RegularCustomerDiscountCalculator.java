package com.shopping.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CustomerDao;
import com.shopping.model.Customer;
import com.shopping.service.DiscountCalculator;

@Service
public class RegularCustomerDiscountCalculator implements DiscountCalculator {
	
	@Autowired
	CustomerDao customerDao;
	
	// These values can be read from properties file
	public static final int SLAB1_DISCOUNT = 0;
	
	public static final int SLAB2_DISCOUNT = 10;
	
	public static final int SLAB3_DISCOUNT = 20;
	
	@Override
	public BigDecimal calculate(int customerId) {
		
		Customer customer = customerDao.getRegularCustomersById(customerId);
		
		double  discountedPrice = 0.0;
		
		double purchasedAmount = customer.getPurchasedAmount().doubleValue();
		
		//Purchased amount is greater than $10000 i.e. $10001 & above
		if(purchasedAmount > 10000) {
			
			discountedPrice += DiscountCalculator.discountCalculate(purchasedAmount,10000,SLAB3_DISCOUNT);
			
			purchasedAmount = purchasedAmount - 5000;
		} 
		
		//Purchased amount is Between 5000 and 10000 (including 5000 and 10000)
		if(purchasedAmount >= 5000 && purchasedAmount <= 10000) {
			
			discountedPrice += DiscountCalculator.discountCalculate(purchasedAmount,5000,SLAB2_DISCOUNT);

			purchasedAmount = purchasedAmount - 5000;
		} 
		
		//Purchased amount is less than or equal to 5000 (i.e. 0 to 5000)
		discountedPrice += purchasedAmount <=5000 ? DiscountCalculator.discountCalculate(purchasedAmount,0,SLAB1_DISCOUNT) : 0;
		
		customer.setDiscountPrice(BigDecimal.valueOf(discountedPrice));
		customer.setFinalAmount(customer.getPurchasedAmount().subtract(customer.getDiscountPrice()));
	
		return customer.getFinalAmount();
	}
}
