package com.jspiders.shoppingcart.service.implementation;

import java.util.List;

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
		Customer customer = customerDao.findCustomerById(customerId);
		List<WishList> wishLists = customer.getWishLists();
		for (WishList wishList2 : wishLists) {
			if (wishList2.getName().equals(wishList.getName())) {
				throw new UserDefinedException("wishlist name already exists with " + wishList.getName());
			}
		}
		WishList list = wishListDao.createWishList(wishList);
		list.setCustomer(customer);
		wishLists.add(list);
		customer.setWishLists(wishLists);
		customerDao.saveCustomer(customer);
		ResponseStructure<List<WishList>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("wishlist created");
		responseStructure.setData(wishLists);
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<Product>> saveProductToWishList(int wishListId, int productId) {
		WishList wishList = wishListDao.findWishListById(wishListId);
		Product product = productDao.findProductByid(productId);

		List<Product> products = wishList.getProducts();
		if (products.isEmpty()) {
			products.add(product);
			wishList.setProducts(products);
			wishListDao.updateWishList(wishList);
			return fetchProductsByWishListID(wishList.getId());
		} else {
			for (Product p : products) {
				if (p.getId() == productId) {
					throw new UserDefinedException("product already exists in wishList " + p.getName());
				}
			}
			products.add(product);
			wishList.setProducts(products);
			wishListDao.updateWishList(wishList);
		}
		return fetchProductsByWishListID(wishList.getId());
	}

	@Override
	public ResponseStructure<List<Product>> fetchProductsByWishListID(int wishListId) {
		WishList wishList = wishListDao.fetchProductsByWishListID(wishListId);
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Products of " + wishList.getName());
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setData(wishList.getProducts());
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<WishList>> fetchWishLists(int customerId) {
		Customer customer = customerDao.findCustomerById(customerId);
		List<WishList> lists = customer.getWishLists();
		if (lists.isEmpty()) {
			throw new UserDefinedException("no wishlists found");
		} else {
			ResponseStructure<List<WishList>> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("WishLists");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(lists);
			return responseStructure;
		}
	}

	@Override
	public ResponseStructure<List<Product>> removeProductInWishListById(int wishListId, int productId) {
		WishList wishList = wishListDao.findWishListById(wishListId);
		List<Product> products = wishList.getProducts();
		Product product = null;
		for (Product p : products) {
			if (p.getId() == productId) {
				product = p;
			}
		}
		if (product != null) {
			products.remove(product);
		} else {
			throw new UserDefinedException("no product found");
		}
		wishList.setProducts(products);
		wishListDao.updateWishList(wishList);
		return fetchProductsByWishListID(wishListId);
	}
}