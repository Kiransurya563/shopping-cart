package com.jspiders.shoppingcart.dao.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.CartDao;
import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.repository.CartRepository;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Optional<Cart> fetchCart(int cartId) {
		return cartRepository.findById(cartId);
	}

}
