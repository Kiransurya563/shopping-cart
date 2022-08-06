package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface CustomerService {

	ResponseStructure<Customer> createCustomer(Customer customer);

	ResponseStructure<Customer> saveCustomer(String password, String token);

	ResponseStructure<Customer> validateCustomer(String email, String Password);

	ResponseStructure<Customer> findCustomerById(int id);

	ResponseStructure<List<Customer>> fetchAllCustomers();
}
