package com.jspiders.shoppingcart.service.implementation;

import java.util.List;

import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.CartService;

public class CartServiceImpl implements CartService{

	@Override
	public ResponseStructure<List<Product>> addToCart(int customerId, int productId) {
		
	}

	@Override
	public ResponseStructure<List<Product>> removeFromCart(int customerId, int productId) {
		
	}

	@Override
	public ResponseStructure<Cart> viewCart(int customerId) {
		
	}

}
