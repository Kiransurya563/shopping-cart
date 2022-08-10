package com.jspiders.shoppingcart.dao.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.CartDao;
import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.exception.UserDefinedException;
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
	public Cart fetchCart(int cartId) {
		Optional<Cart> optional = cartRepository.findById(cartId);
		try {
			return optional.get();
		} catch (Exception e) {
			throw new UserDefinedException("Could not find cart, check cart id");
		}
	}

}
