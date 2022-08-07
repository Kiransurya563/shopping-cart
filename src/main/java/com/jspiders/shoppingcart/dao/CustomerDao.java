package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Customer;

public interface CustomerDao {

	Customer saveCustomer(Customer customer);

	Customer validateCustomer(String email, String password);

	Optional<Customer> findCustomerById(int customerId);

	List<Customer> fetchAllCustomers();

}
