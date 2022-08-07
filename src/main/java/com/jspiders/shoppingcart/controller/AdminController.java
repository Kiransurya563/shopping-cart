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
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.implementation.AdminServiceImpl;
import com.jspiders.shoppingcart.service.implementation.MerchantServiceImpl;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	AdminServiceImpl adminServiceImpl;

	@Autowired
	MerchantServiceImpl merchantServiceImpl;

	@PostMapping("/save")
	public ResponseStructure<Admin> saveAdmin(@RequestBody Admin admin) {
		return adminServiceImpl.saveAdmin(admin);
	}

	@PostMapping("/login")
	public ResponseStructure<Admin> validateAdmin(@RequestBody Login login) {
		return adminServiceImpl.validateAdmin(login.getEmail(), login.getPassword());
	}

	@GetMapping("/merchants")
	public ResponseStructure<List<Merchant>> fetchAllMerchants() {
		return merchantServiceImpl.fetchAllMerchant();
	}

	@PutMapping("/merchants/status/{merchantId}")
	public ResponseStructure<List<Merchant>> changeMerchantStatus(@PathVariable int merchantId) {
		return merchantServiceImpl.changeMerchantStatus(merchantId);
	}
}
