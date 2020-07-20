package com.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.shopping.dao.CustomerDao;
import com.shopping.model.Customer;
import com.shopping.model.CustomerType;
import com.shopping.service.impl.PremiumCustomerDiscountCalculator;

@RunWith(MockitoJUnitRunner.class)
public class PremiumCustomerDiscountCalculatorTest {

	@Mock
	CustomerDao customerDao;
	
	@InjectMocks
	PremiumCustomerDiscountCalculator discountCalculator;
	
	@Test
	public void testDiscountForFirstRegularCustomer() {
		Customer customer = new Customer(1,"Mayur",CustomerType.PREMIUM,BigDecimal.valueOf(4000.0));
		when(customerDao.getRegularCustomersById(1)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(3600.0), discountCalculator.calculate(1),"Should return the final discounted amount i.e. $3600 ");
	}
	
	@Test
	public void testDiscountForSecondCustomer() {
		Customer customer = new Customer(2,"Priyanka",CustomerType.PREMIUM,BigDecimal.valueOf(8000.0));
		when(customerDao.getRegularCustomersById(2)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(7000.0), discountCalculator.calculate(2),"Should return the final discounted amount $7000 ");
	}
	
	@Test
	public void testDiscountForThirdCustomer() {
		Customer customer = new Customer(3,"Rupesh",CustomerType.PREMIUM,BigDecimal.valueOf(12000.0));
		when(customerDao.getRegularCustomersById(3)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(10200.0), discountCalculator.calculate(3),"Should return the final discounted amount $10200 ");
	}
	
	@Test
	public void testDiscountForFourthCustomer() {
		Customer customer = new Customer(4,"Vishal",CustomerType.PREMIUM,BigDecimal.valueOf(20000.0));
		when(customerDao.getRegularCustomersById(4)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(15800.0), discountCalculator.calculate(4),"Should return the final discounted amount $15800 ");
	}
	
	@Test(expected = NullPointerException.class)
	public void testDiscountForPurchasedAmountIsNull() {
		Customer customer = new Customer(1,"Mayur",CustomerType.PREMIUM,null);
		when(customerDao.getRegularCustomersById(1)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(4090.0), discountCalculator.calculate(1),"Should return the final discounted amount i.e. $5000 ");
	}
}
