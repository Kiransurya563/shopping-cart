package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface WishListService {
	ResponseStructure<List<WishList>> createWishList(int customerId, WishList wishList);

	ResponseStructure<List<Product>> saveProductToWishList(int wishListId, int productId);

	ResponseStructure<List<Product>> fetchProductsByWishListID(int wishListId);

	ResponseStructure<List<WishList>> fetchWishLists(int customerId);

	ResponseStructure<List<Product>> removeProductInWishListById(int wishListId, int productId);
}
