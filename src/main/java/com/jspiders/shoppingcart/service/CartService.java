package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface CartService {
	ResponseStructure<List<Product>> addToCart(int customerId, int productId);

	ResponseStructure<Cart> removeFromCart(int customerId, int productId);

	ResponseStructure<Cart> viewCart(int customerId);
}
