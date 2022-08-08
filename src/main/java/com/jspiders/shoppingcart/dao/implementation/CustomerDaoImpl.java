package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.repository.CustomerRepository;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer validateCustomer(String email, String password) {
		return customerRepository.validateCustomer(email, password);
	}

	@Override
	public Optional<Customer> findCustomerById(int customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}

}
