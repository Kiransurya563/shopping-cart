package com.jspiders.shoppingcart.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.CustomerMailVerification;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.CustomerService;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	CustomerMailVerification customerMailVerification;

	Customer customer;

	@Override
	public ResponseStructure<Customer> createCustomer(Customer customer) {
		String token = RandomString.make(32);
		customer.setToken(token);
		this.customer = customer;

		customerMailVerification.SendVerificationEmail(customer);

		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();

		structure.setData(customer);
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Check mail for verification link");
		return structure;
	}

	@Override
	public ResponseStructure<Customer> saveCustomer(String token, String password) {
		if (token.equals(customer.getToken())) {
			customer.setPassword(password);
			customer.setToken(null);

			Customer customer1 = customerDao.saveCustomer(customer);

			ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
			if (customer1 != null) {
				structure.setData(customer1);
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Account Registered succesfully");
				return structure;
			} else {
				throw new UserDefinedException("Account did not get Created");
			}
		} else {
			throw new UserDefinedException("Token mismatch check your link and try again");
		}
	}

	@Override
	public ResponseStructure<Customer> validateCustomer(String email, String password) {
		Customer customer = customerDao.validateCustomer(email, password);
		if (customer == null) {
			throw new UserDefinedException("Login Failed, check email and password and try again");
		} else {
			ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
			structure.setData(customer);
			structure.setMessage("Login Succesfull");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return structure;
		}
	}

	@Override
	public ResponseStructure<Customer> findCustomerById(int customerId) {
		Customer customer = customerDao.findCustomerById(customerId);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		structure.setData(customer);
		structure.setMessage("Data found...");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return structure;
	}

	@Override
	public ResponseStructure<List<Customer>> fetchAllCustomers() {
		List<Customer> list = customerDao.fetchAllCustomers();
		if (list.isEmpty()) {
			throw new UserDefinedException("No data found...");
		} else {
			ResponseStructure<List<Customer>> structure = new ResponseStructure<List<Customer>>();
			structure.setData(list);
			structure.setMessage("Data found...");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return structure;
		}
	}

}