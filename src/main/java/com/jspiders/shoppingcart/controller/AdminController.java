package com.jspiders.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.implementation.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/save")
	public ResponseStructure<Admin> saveAdmin(@RequestBody Admin admin) {
		return adminServiceImpl.saveAdmin(admin);
	}
	
	@PostMapping("/login")
	public ResponseStructure<Admin> validateAdmin(@RequestBody Login login){
		return adminServiceImpl.validateAdmin(login.getEmail(), login.getPassword());
	}
}
