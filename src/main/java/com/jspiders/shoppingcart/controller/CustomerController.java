package com.jspiders.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.CustomerService;
import com.jspiders.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@PostMapping("/save")
	public ResponseStructure<Customer> saveCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PutMapping("/save/{token}")
	public ResponseStructure<Customer> verifyCustomer(@RequestBody Password password, @PathVariable String token) {
		return customerService.saveCustomer(token, password.getPassword());
	}

	@PostMapping("/login")
	public ResponseStructure<Customer> validateCustomer(@RequestBody Login login) {
		return customerService.validateCustomer(login.getEmail(), login.getPassword());
	}

	@GetMapping("/products")
	public ResponseStructure<List<Product>> fetchAllProducts() {
		return productService.fetchAllProducts();
	}
}
