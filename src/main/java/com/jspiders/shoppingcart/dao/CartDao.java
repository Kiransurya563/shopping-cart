package com.jspiders.shoppingcart.dao;

import com.jspiders.shoppingcart.dto.Cart;

public interface CartDao {

	Cart saveCart(Cart cart);

	Cart fetchCart(int cartId);
}
