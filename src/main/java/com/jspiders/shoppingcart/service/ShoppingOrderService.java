package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.ShoppingOrder;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface ShoppingOrderService {
	ResponseStructure<ShoppingOrder> placeOrder(int customerId, int addressId);

	ResponseStructure<ShoppingOrder> getOrderById(int orderId);

	ResponseStructure<List<ShoppingOrder>> getCustomerAllOrders(int customerId);

	ResponseStructure<ShoppingOrder> cancelOrder(int orderId);
}
