package com.jspiders.shoppingcart.service;

import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface WishListService {
	ResponseStructure<Product> createWishList(int productId,int customerId );

	
}
