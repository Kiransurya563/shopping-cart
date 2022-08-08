package com.jspiders.shoppingcart.dao;

import java.util.Optional;

import com.jspiders.shoppingcart.dto.Cart;

public interface CartDao {

	Cart saveCart(Cart cart);

	Optional<Cart> fetchCart(int cartId);
}
