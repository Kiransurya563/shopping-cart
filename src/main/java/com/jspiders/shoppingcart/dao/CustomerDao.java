package com.jspiders.shoppingcart.dao;

import java.util.List;

import com.jspiders.shoppingcart.dto.Customer;

public interface CustomerDao {

	Customer saveCustomer(Customer customer);

	Customer validateCustomer(String email, String password);

	Customer findCustomerById(int customerId);

	List<Customer> fetchAllCustomers();

}
