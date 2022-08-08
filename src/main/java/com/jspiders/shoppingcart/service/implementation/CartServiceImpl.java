package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.CartDao;
import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dto.Cart;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Item;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.CartService;
import com.jspiders.shoppingcart.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductService productService;

	@Autowired
	CartDao cartDao;

	@Override
	public ResponseStructure<List<Product>> addToCart(int customerId, int productId) {
		Optional<Customer> opCustomer = customerDao.findCustomerById(customerId);
		Optional<Product> opProduct = productDao.findProductByid(productId);

		if (opCustomer.isEmpty()) {
			throw new UserDefinedException("Customer Id did not match any");
		} else {
			Customer customer = opCustomer.get();

			Cart cart = customer.getCart();
			if(cart==null)
			{
			cart=new Cart();
			}
			List<Item> list1 = cart.getItems();
			
			
			if (opProduct.isEmpty()) {
				throw new UserDefinedException("Product Not found");
			} else {
				Product product = opProduct.get();
				List<Item> list = cart.getItems().stream().filter(i -> i.getName().equals(product.getName()))
						.collect(Collectors.toList());

				if (list.isEmpty()) {
					Item item = new Item();
					item.setName(product.getName());
					item.setPrice(product.getPrice());
					item.setQuantity(1);
					item.setCart(cart);
					
					list1.add(item);
				} else {
					Item item2 = list.get(0);
					item2.setQuantity(item2.getQuantity() + 1);
					list1.remove(0);
					list1.add(item2);
				}
				cart.setCustomer(customer);
				cart.setItems(list1);

				customer.setCart(cart);

				customerDao.saveCustomer(customer);
				cartDao.saveCart(cart);

			}
		}
		return productService.fetchAllProducts();
	}

	@Override
	public ResponseStructure<List<Product>> removeFromCart(int customerId, int productId) {
		Optional<Customer> opCustomer = customerDao.findCustomerById(customerId);
		Optional<Product> opProduct = productDao.findProductByid(productId);

		if (opCustomer.isEmpty()) {
			throw new UserDefinedException("Customer Id did not match any");
		} else {
			Customer customer = opCustomer.get();

			Cart cart = customer.getCart();
			List<Item> list1 = cart.getItems();
			if (opProduct.isEmpty()) {
				throw new UserDefinedException("Product Not found");
			} else {
				Product product = opProduct.get();
				List<Item> list = cart.getItems().stream().filter(i -> i.getName().equals(product.getName()))
						.collect(Collectors.toList());

				if (list.isEmpty()) {
					throw new UserDefinedException("No such product present in cart");
				} else {
					Item item2 = list.get(0);
					if (item2.getQuantity() > 1) {
						item2.setQuantity(item2.getQuantity() - 1);
						list1.remove(0);
						list1.add(item2);
					} else {
						list1.remove(item2);
					}
				}
				cart.setCustomer(customer);
				cart.setItems(list1);

				customer.setCart(cart);

				customerDao.saveCustomer(customer);
				cartDao.saveCart(cart);

			}
		}
		return productService.fetchAllProducts();
	}

	@Override
	public ResponseStructure<Cart> viewCart(int customerId) {
		Optional<Customer> opCustomer = customerDao.findCustomerById(customerId);
		if (opCustomer.isEmpty()) {
			throw new UserDefinedException("Customer Id did not match any");
		} else {
			Customer customer = opCustomer.get();
			Cart cart = customer.getCart();

			ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
			structure.setData(cart);
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Data present in cart");
			return structure;
		}
	}

}
