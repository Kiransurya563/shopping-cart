package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dao.WishListDao;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.ProductService;
import com.jspiders.shoppingcart.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	WishListDao wishListDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;

	@Override
	public ResponseStructure<List<WishList>> createWishList(int customerId, WishList wishList) {
		Optional<Customer> customer = customerDao.findCustomerById(customerId);
		Customer customer2 = customer.get();
		WishList list = wishListDao.createWishList(wishList);
		list.setCustomer(customer2);
		List<WishList> wishLists = customer2.getWishLists();
		wishLists.add(list);
		customer2.setWishLists(wishLists);
		customerDao.saveCustomer(customer2);
		ResponseStructure<List<WishList>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("wishlist created");
		responseStructure.setData(wishLists);
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<Product>> saveProductToWishList(int customerId, int wishListId, int productId) {
		Optional<Customer> customer = customerDao.findCustomerById(customerId);
		Customer customer2 = customer.get();
		Optional<WishList> wishList = wishListDao.findWishListById(wishListId);
		WishList wishList2 = wishList.get();
		Optional<Product> product = productDao.findProductByid(productId);
		Product product2 = product.get();

		List<Product> products = wishList2.getProducts();
		for (Product product3 : products) {
			if (product3.getId() == product2.getId()) {
				throw new UserDefinedException("product already exists in wishList");
			} else {
				products.add(product2);
				wishList2.setProducts(products);

				List<WishList> wishLists = customer2.getWishLists();
				wishLists.add(wishList2);
				wishListDao.createWishList(wishList2);
				
				customer2.setWishLists(wishLists);

				customerDao.saveCustomer(customer2);
				wishListDao.createWishList(wishList2);

				return productService.fetchAllProducts();
			}
		}
	}
}
