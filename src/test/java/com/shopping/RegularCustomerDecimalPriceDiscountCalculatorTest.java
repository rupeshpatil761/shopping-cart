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
import com.shopping.service.impl.RegularCustomerDecimalPriceDiscountCalculator;

@RunWith(MockitoJUnitRunner.class)
public class RegularCustomerDecimalPriceDiscountCalculatorTest {

	@Mock
	CustomerDao customerDao;
	
	@InjectMocks
	RegularCustomerDecimalPriceDiscountCalculator discountCalculator;
	
	@Test
	public void testDiscountForFirstRegularCustomer() {
		Customer customer = new Customer(1,"Mayur",CustomerType.REGULAR,BigDecimal.valueOf(5000.0));
		when(customerDao.getRegularCustomersById(1)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(5000.0), discountCalculator.calculate(1),"Should return the final discounted amount i.e. $5000 ");
	}
	
	@Test
	public void testDiscountForSecondCustomer() {
		Customer customer = new Customer(2,"Manas",CustomerType.REGULAR,BigDecimal.valueOf(10000.0));
		when(customerDao.getRegularCustomersById(2)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(9500.0), discountCalculator.calculate(2),"Should return the final discounted amount $9500 ");
	}
	
	@Test
	public void testDiscountForThirdCustomer() {
		Customer customer = new Customer(3,"Manas",CustomerType.REGULAR,BigDecimal.valueOf(15000.0));
		when(customerDao.getRegularCustomersById(3)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(13500.0), discountCalculator.calculate(3),"Should return the final discounted amount $13500 ");
	}
	
	@Test(expected = NullPointerException.class)
	public void testDiscountForPurchasedAmountIsNull() {
		Customer customer = new Customer(1,"Mayur",CustomerType.REGULAR,null);
		when(customerDao.getRegularCustomersById(1)).thenReturn(customer);
		assertEquals(BigDecimal.valueOf(4090.0), discountCalculator.calculate(1),"Should return the final discounted amount i.e. $5000 ");
	}
}
