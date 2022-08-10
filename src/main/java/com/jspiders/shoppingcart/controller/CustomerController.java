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

import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.CartService;
import com.jspiders.shoppingcart.service.CustomerService;
import com.jspiders.shoppingcart.service.ProductService;
import com.jspiders.shoppingcart.service.WishListService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	WishListService wishListService;

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

	@PostMapping("/carts/{customerId}/{productId}")
	public ResponseStructure<List<Product>> addToCart(@PathVariable int customerId, @PathVariable int productId) {
		return cartService.addToCart(customerId, productId);
	}

	@GetMapping("/carts/{customerId}")
	public ResponseStructure<Cart> viewCart(@PathVariable int customerId) {
		return cartService.viewCart(customerId);
	}

	@DeleteMapping("/carts/{customerId}/{productId}")
	public ResponseStructure<Cart> removeFromCart(@PathVariable int customerId, @PathVariable int productId) {
		return cartService.removeFromCart(customerId, productId);
	}

	@PostMapping("/wishlists/{customerId}")
	public ResponseStructure<List<WishList>> createWishList(@PathVariable int customerId,
			@RequestBody WishList wishList) {
		return wishListService.createWishList(customerId, wishList);
	}

	@PutMapping("/wishlists/{wishListId}/{productId}")
	public ResponseStructure<List<Product>> saveProductToWishList(@PathVariable int wishListId,
			@PathVariable int productId) {
		return wishListService.saveProductToWishList(wishListId, productId);
	}

	@GetMapping("/wishlists/{customerId}")
	public ResponseStructure<List<WishList>> fetchWishLists(@PathVariable int customerId) {
		return wishListService.fetchWishLists(customerId);
	}

	@GetMapping("wishlists/products/{wishListId}")
	public ResponseStructure<List<Product>> fetchProductsByWishListID(@PathVariable int wishListId) {
		return wishListService.fetchProductsByWishListID(wishListId);
	}

	@DeleteMapping("wishlists/{wishListId}/{productId}")
	public ResponseStructure<List<Product>> removeProductInWishListById(@PathVariable int wishListId,
			@PathVariable int productId) {
		return wishListService.removeProductInWishListById(wishListId, productId);
	}
}