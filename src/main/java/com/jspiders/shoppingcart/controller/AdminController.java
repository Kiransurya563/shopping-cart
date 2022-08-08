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

import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.AdminService;
import com.jspiders.shoppingcart.service.CustomerService;
import com.jspiders.shoppingcart.service.MerchantService;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	AdminService adminService;

	@Autowired
	MerchantService merchantService;

	@Autowired
	CustomerService customerService;

	@PostMapping("/save")
	public ResponseStructure<Admin> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@PostMapping("/login")
	public ResponseStructure<Admin> validateAdmin(@RequestBody Login login) {
		return adminService.validateAdmin(login.getEmail(), login.getPassword());
	}

	@GetMapping("/merchants")
	public ResponseStructure<List<Merchant>> fetchAllMerchants() {
		return merchantService.fetchAllMerchant();
	}

	@GetMapping("/customers")
	public ResponseStructure<List<Customer>> fetchAllCustomers() {
		return customerService.fetchAllCustomers();
	}

	@PutMapping("/merchants/status/{merchantId}")
	public ResponseStructure<List<Merchant>> changeMerchantStatus(@PathVariable int merchantId) {
		return merchantService.changeMerchantStatus(merchantId);
	}
}
