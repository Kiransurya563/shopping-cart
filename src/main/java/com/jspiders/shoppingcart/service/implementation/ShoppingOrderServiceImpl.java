package com.jspiders.shoppingcart.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.AddressDao;
import com.jspiders.shoppingcart.dao.CartDao;
import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dao.ShoppingOrderDao;
import com.jspiders.shoppingcart.dto.Address;
import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Item;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.ShoppingOrder;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.ShoppingOrderService;

@Service
public class ShoppingOrderServiceImpl implements ShoppingOrderService {
	@Autowired
	CustomerDao customerDao;

	@Autowired
	ShoppingOrderDao shoppingOrderDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	AddressDao addressDao;

	@Override
	public ResponseStructure<ShoppingOrder> placeOrder(int customerId, int addressId) {

		Customer customer = customerDao.findCustomerById(customerId);
		Address address = addressDao.fetchAddressById(addressId);

		Cart cart = customer.getCart();
		if (cart == null) {
			throw new UserDefinedException("Nothing is there in cart");
		} else {
			List<Item> items = cart.getItems();
			List<ShoppingOrder> list = customer.getShoppingOrders();
			if (list == null) {
				list = new ArrayList<>();
			}

			if (items.isEmpty()) {
				throw new UserDefinedException("No products present in cart");
			} else {
				ShoppingOrder shoppingOrder = new ShoppingOrder();
				List<Item> shoppingList = new ArrayList<>();

				for (Item item : items) {
					Product product = productDao.findProductByName(item.getName());
					if (product.getStock() < item.getQuantity()) {
						throw new UserDefinedException("Out of Stock");
					} else {

						product.setStock(product.getStock() - item.getQuantity());
						productDao.saveProduct(product);
						shoppingList.add(item);
					}
				}
				shoppingOrder.setItems(shoppingList);
				shoppingOrder.setAddress(address);
				shoppingOrder.setCustomer(customer);

				shoppingOrderDao.saveOrder(shoppingOrder);
				customer.setCart(null);
				customerDao.saveCustomer(customer);
				ResponseStructure<ShoppingOrder> responseStructure = new ResponseStructure<ShoppingOrder>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Order Placed");
				responseStructure.setData(shoppingOrder);
				return responseStructure;

			}
		}

	}

	@Override
	public ResponseStructure<ShoppingOrder> getOrderById(int orderId) {
		ShoppingOrder shoppingOrder = shoppingOrderDao.getOrderById(orderId);
		ResponseStructure<ShoppingOrder> responseStructure = new ResponseStructure<ShoppingOrder>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Order found");
		responseStructure.setData(shoppingOrder);
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<ShoppingOrder>> getCustomerAllOrders(int customerId) {
		Customer customer = customerDao.findCustomerById(customerId);

		ResponseStructure<List<ShoppingOrder>> responseStructure = new ResponseStructure<List<ShoppingOrder>>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Orders found");
		responseStructure.setData(customer.getShoppingOrders());
		return responseStructure;
	}

	@Override
	public ResponseStructure<ShoppingOrder> cancelOrder(int orderId) {
		ShoppingOrder shoppingOrder = shoppingOrderDao.getOrderById(orderId);
		List<Item> items = shoppingOrder.getItems();

		for (Item item : items) {
			Product product = productDao.findProductByName(item.getName());
			if (product == null) {
				throw new UserDefinedException("No such product in order");
			} else {
				product.setStock(product.getStock() + item.getQuantity());
				productDao.saveProduct(product);
			}
		}
		ResponseStructure<ShoppingOrder> responseStructure = new ResponseStructure<ShoppingOrder>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Orders Succesfully cancelled");
		responseStructure.setData(shoppingOrderDao.cancelOrder(shoppingOrder));
		return responseStructure;

	}

}
