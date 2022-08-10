package com.jspiders.shoppingcart.dao;

import com.jspiders.shoppingcart.dto.WishList;

public interface WishListDao {

	WishList createWishList(WishList wishList);

	WishList findWishListById(int wishListId);

	WishList updateWishList(WishList wishList);

	WishList fetchProductsByWishListID(int wishListId);

}
