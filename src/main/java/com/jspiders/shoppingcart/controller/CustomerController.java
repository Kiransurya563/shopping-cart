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

import com.jspiders.shoppingcart.dto.Address;
import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.ShoppingOrder;
import com.jspiders.shoppingcart.helper.Login;
import com.jspiders.shoppingcart.helper.Password;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.AddressService;
import com.jspiders.shoppingcart.service.CartService;
import com.jspiders.shoppingcart.service.CustomerService;
import com.jspiders.shoppingcart.service.ProductService;
import com.jspiders.shoppingcart.service.ShoppingOrderService;

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
	ShoppingOrderService shoppingOrderService;
	@Autowired
	AddressService addressService;

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
	public ResponseStructure<List<Product>> removeFromCart(@PathVariable int customerId, @PathVariable int productId) {
		return cartService.removeFromCart(customerId, productId);
	}

	@PostMapping("/addresses/{customerId}")
	public ResponseStructure<Address> addAddress(@PathVariable int customerId, @RequestBody Address address) {
		return addressService.addAddress(address, customerId);
	}

	@DeleteMapping("/addresses/{addressId}")
	public ResponseStructure<Address> removeAddress(@PathVariable int addressId) {
		return addressService.removeAddress(addressId);
	}

	@GetMapping("/addresse/{addressId}")
	public ResponseStructure<Address> fetchAddressById(@PathVariable int addressId) {
		return addressService.fetchAddressById(addressId);
	}
	
	@GetMapping("/addresses/{customerId}")
	public ResponseStructure<List<Address>> fetchCustomerAllAddresses(@PathVariable int customerId) {
		return addressService.fetchCustomerAllAddresses(customerId);
	}

	@PostMapping("/orders/{customerId}/{addressId}")
	public ResponseStructure<ShoppingOrder> placeOrder(@PathVariable int customerId,@PathVariable int addressId) {
		return shoppingOrderService.placeOrder(customerId,addressId);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseStructure<ShoppingOrder> getOrderById(@PathVariable int orderId) {
		return shoppingOrderService.getOrderById(orderId);
	}

	@GetMapping("/orders/{customerId}")
	public ResponseStructure<List<ShoppingOrder>> getCustomerAllOrders(@PathVariable int customerId) {
		return shoppingOrderService.getCustomerAllOrders(customerId);
	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseStructure<ShoppingOrder> cancelOrder(@PathVariable int orderId) {
		return shoppingOrderService.cancelOrder(orderId);
	}

}
