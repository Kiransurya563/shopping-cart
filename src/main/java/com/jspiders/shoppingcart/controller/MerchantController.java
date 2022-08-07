package com.jspiders.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.implementation.MerchantServiceImpl;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	MerchantServiceImpl merchantServiceImpl;

	@PostMapping("/save")
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		return merchantServiceImpl.createMerchant(merchant);
	}

	@PutMapping("/save/{token}")
	public ResponseStructure<Merchant> verifyMerchant(@RequestBody Password password, @PathVariable String token) {
		return merchantServiceImpl.saveMerchant(token, password.getPassword());
	}

	@PostMapping("/login")
	public ResponseStructure<Merchant> validateMerchant(@RequestBody Login login) {
		return merchantServiceImpl.validateMerchant(login.getEmail(), login.getPassword());
	}

}
