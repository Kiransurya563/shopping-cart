package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
