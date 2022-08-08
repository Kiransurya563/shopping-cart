package com.jspiders.shoppingcart.dao;

import java.util.Optional;

import com.jspiders.shoppingcart.dto.ShoppingOrder;

public interface ShoppingOrderDao {
	ShoppingOrder saveOrder(ShoppingOrder shoppingOrder);

	Optional<ShoppingOrder> getOrderById(int orderId);

	ShoppingOrder cancelOrder(ShoppingOrder shoppingOrder);
}
