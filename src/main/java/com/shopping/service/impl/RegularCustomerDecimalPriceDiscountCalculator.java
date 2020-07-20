package com.shopping.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CustomerDao;
import com.shopping.model.Customer;
import com.shopping.service.DiscountCalculator;

@Service
public class RegularCustomerDecimalPriceDiscountCalculator implements DiscountCalculator {
	
	@Autowired
	CustomerDao customerDao;
	
	// These values can be read from properties file
	
	public static final BigDecimal SLAB1_DISCOUNT = BigDecimal.valueOf(0);
	
	public static final BigDecimal SLAB2_DISCOUNT = BigDecimal.valueOf(10);
	
	public static final BigDecimal SLAB3_DISCOUNT = BigDecimal.valueOf(20);
	
	public static final BigDecimal SLAB1_DEDUCTION = BigDecimal.valueOf(0);
	
	public static final BigDecimal SLAB2_DEDUCTION = BigDecimal.valueOf(5000);
	
	public static final BigDecimal SLAB3_DEDUCTION = BigDecimal.valueOf(10000);
	
	@Override
	public BigDecimal calculate(int customerId) {
		
		Customer customer = customerDao.getRegularCustomersById(customerId);
		
		BigDecimal  discountedPrice = BigDecimal.valueOf(0);
		
		BigDecimal purchasedAmount = customer.getPurchasedAmount();
		
		//Purchased amount is greater than $10000 i.e. $10001 & above
		if(comparePrices(purchasedAmount, SLAB3_DEDUCTION) > 0 ) {
			
			discountedPrice = DiscountCalculator.bigDecimalPricediscountCalculate(purchasedAmount,SLAB3_DEDUCTION,SLAB3_DISCOUNT);
			purchasedAmount = purchasedAmount.subtract(SLAB2_DEDUCTION);
		} 
		
		//Purchased amount is Between 5000 and 10000 (including 5000 and 10000)
		if(comparePrices(purchasedAmount, SLAB2_DEDUCTION) >= 0  && comparePrices(purchasedAmount, SLAB3_DEDUCTION) <= 0) {
			
			discountedPrice = discountedPrice.add(DiscountCalculator.bigDecimalPricediscountCalculate(purchasedAmount,SLAB2_DEDUCTION,SLAB2_DISCOUNT));
			purchasedAmount = purchasedAmount.subtract(SLAB2_DEDUCTION);
		} 
		
		//Purchased amount is less than or equal to 5000 (i.e. 0 to 5000)
		discountedPrice = discountedPrice.add(comparePrices(purchasedAmount, SLAB2_DEDUCTION) <= 0 ? DiscountCalculator.bigDecimalPricediscountCalculate(purchasedAmount,SLAB1_DEDUCTION,SLAB1_DISCOUNT) : SLAB1_DEDUCTION);
		
		customer.setDiscountPrice(discountedPrice);
		customer.setFinalAmount(customer.getPurchasedAmount().subtract(customer.getDiscountPrice()));
	
		return customer.getFinalAmount();
	}
	
	public int comparePrices(BigDecimal purchasedAmount, BigDecimal slab) {
		return purchasedAmount.compareTo(slab);
    }
}
