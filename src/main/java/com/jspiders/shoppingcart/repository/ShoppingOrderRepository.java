package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {

}
