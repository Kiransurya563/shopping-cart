package com.jspiders.shoppingcart.dao.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.ShoppingOrderDao;
import com.jspiders.shoppingcart.dto.ShoppingOrder;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.repository.ShoppingOrderRepository;

@Repository
public class ShoppingOrderDaoImpl implements ShoppingOrderDao {

	@Autowired
	ShoppingOrderRepository shoppingOrderrepository;

	@Override
	public ShoppingOrder saveOrder(ShoppingOrder shoppingOrder) {
		return shoppingOrderrepository.save(shoppingOrder);
	}

	@Override
	public ShoppingOrder getOrderById(int orderId) {
		Optional<ShoppingOrder> optional = shoppingOrderrepository.findById(orderId);
		try {
			return optional.get();
		} catch (Exception e) {
			throw new UserDefinedException("Could not find order, check order id");
		}
	}

	@Override
	public ShoppingOrder cancelOrder(ShoppingOrder shoppingOrder) {
		shoppingOrderrepository.delete(shoppingOrder);
		return shoppingOrder;
	}
}
