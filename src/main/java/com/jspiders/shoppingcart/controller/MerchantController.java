package com.jspiders.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.MerchantService;
import com.jspiders.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	MerchantService merchantService;

	@Autowired
	ProductService productService;

	@PostMapping("/save")
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.createMerchant(merchant);
	}

	@PutMapping("/save/{token}")
	public ResponseStructure<Merchant> verifyMerchant(@RequestBody Password password, @PathVariable String token) {
		return merchantService.saveMerchant(token, password.getPassword());
	}

	@PostMapping("/login")
	public ResponseStructure<Merchant> validateMerchant(@RequestBody Login login) {
		return merchantService.validateMerchant(login.getEmail(), login.getPassword());
	}

	@PostMapping("/products/{merchantId}")
	public ResponseStructure<Product> saveProduct(@RequestBody Product product, @PathVariable int merchantId) {
		return productService.saveProduct(product, merchantId);
	}

	@GetMapping("/products/{merchantId}")
	public ResponseStructure<List<Product>> fetchMerchantAllProducts(@PathVariable int merchantId) {
		return productService.fetchMerchantAllProducts(merchantId);
	}

	@GetMapping("/product/{productId}")
	public ResponseStructure<Product> fetchProductById(@PathVariable int productId) {
		return productService.fetchProductById(productId);
	}

	@PutMapping("/products/{merchantId}")
	public ResponseStructure<List<Product>> updateProduct(@RequestBody Product product, @PathVariable int merchantId) {
		return productService.updateProduct(product, merchantId);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseStructure<List<Product>> deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}
	
	@DeleteMapping("/delete/{merchantId}")
	public ResponseStructure<Merchant> deleteMerchant(@PathVariable int merchantId) {
		return merchantService.deleteMerchant(merchantId);
	}
}
