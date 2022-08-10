package com.jspiders.shoppingcart.dao;

import com.jspiders.shoppingcart.dto.ShoppingOrder;

public interface ShoppingOrderDao {
	ShoppingOrder saveOrder(ShoppingOrder shoppingOrder);

	ShoppingOrder getOrderById(int orderId);

	ShoppingOrder cancelOrder(ShoppingOrder shoppingOrder);
}
