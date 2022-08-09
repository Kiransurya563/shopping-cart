package com.jspiders.shoppingcart.dao;

import java.util.Optional;

import com.jspiders.shoppingcart.dto.WishList;

public interface WishListDao {

	WishList createWishList(WishList wishList);

	Optional<WishList> findWishListById(int wishListId);

}
