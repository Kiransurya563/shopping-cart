package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface WishListService {
	ResponseStructure<List<WishList>> createWishList(int customerId, WishList wishList);

	ResponseStructure<List<Product>> saveProductToWishList(int customerId, int wishListId, int productId);
}
