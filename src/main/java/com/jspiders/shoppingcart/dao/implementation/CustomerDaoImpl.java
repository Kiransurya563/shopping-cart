package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.exception.UserDefinedException;
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
	public Customer findCustomerById(int customerId) {

		Optional<Customer> optional = customerRepository.findById(customerId);
		try {
			return optional.get();
		} catch (Exception e) {
			throw new UserDefinedException("Could not find customer, check customer id");
		}
	}

	@Override
	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}

}
