package com.shopping.dao;

import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;

@Repository
public interface CustomerDao {
	
	/**
	 * Some dummy data
	 * Actually this should talk to some database to get all the data
	 */
	
	// Returns the Regular type customer based on id
	public Customer getRegularCustomersById(int id);
	
}
