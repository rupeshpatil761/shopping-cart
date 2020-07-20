package com.shopping.service;

import java.math.BigDecimal;

public interface DiscountCalculator {

	BigDecimal calculate(int customerId);
	
	static double discountCalculate(double purchasedAmount, double deductionAmount, int discountPercent) {
		return((purchasedAmount - deductionAmount) * discountPercent) / 100;
	}
	
	
	static BigDecimal bigDecimalPricediscountCalculate(BigDecimal purchasedAmount, BigDecimal deductionAmount, BigDecimal discountPercent) {
		return purchasedAmount.subtract(deductionAmount).multiply(discountPercent).divide(BigDecimal.valueOf(100));
	}
}
