package com.jspiders.shoppingcart.dao.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspiders.shoppingcart.dao.ShoppingOrderDao;
import com.jspiders.shoppingcart.dto.ShoppingOrder;
import com.jspiders.shoppingcart.repository.ShoppingOrderRepository;

public class ShoppingOrderDaoImpl implements ShoppingOrderDao {

	@Autowired
	ShoppingOrderRepository shoppingOrderrepository;

	@Override
	public ShoppingOrder saveOrder(ShoppingOrder shoppingOrder) {
		return shoppingOrderrepository.save(shoppingOrder);
	}

	@Override
	public Optional<ShoppingOrder> getOrderById(int orderId) {
		return shoppingOrderrepository.findById(orderId);
	}

	@Override
	public ShoppingOrder cancelOrder(ShoppingOrder shoppingOrder) {
		shoppingOrderrepository.delete(shoppingOrder);
		return shoppingOrder;
	}
}
