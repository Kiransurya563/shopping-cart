package com.jspiders.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.implementation.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@PostMapping("/save")
	public ResponseStructure<Customer> saveCustomer(@RequestBody Customer customer) {
		return customerServiceImpl.createCustomer(customer);
	}

	@PutMapping("/save/{token}")
	public ResponseStructure<Customer> verifyCustomer(@RequestBody Password password, @PathVariable String token) {
		return customerServiceImpl.saveCustomer(token, password.getPassword());
	}

	@PostMapping("/login")
	public ResponseStructure<Customer> validateCustomer(@RequestBody Login login) {
		return customerServiceImpl.validateCustomer(login.getEmail(), login.getPassword());
	}
}
