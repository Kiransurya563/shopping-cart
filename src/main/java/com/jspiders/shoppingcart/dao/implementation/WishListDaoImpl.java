package com.jspiders.shoppingcart.dao.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.WishListDao;
import com.jspiders.shoppingcart.dto.WishList;
import com.jspiders.shoppingcart.repository.WishListRepository;

@Repository
public class WishListDaoImpl implements WishListDao {
	@Autowired
	WishListRepository wishListRepository;

	@Override
	public WishList createWishList(WishList wishList) {
		return wishListRepository.save(wishList);
	}

	@Override
	public WishList findWishListById(int wishListId) {
		Optional<WishList> wishOptional = wishListRepository.findById(wishListId);
		return wishOptional.get();
	}

	@Override
	public WishList updateWishList(WishList wishList) {
		return wishListRepository.save(wishList);
	}

	@Override
	public WishList fetchProductsByWishListID(int wishListId) {
		Optional<WishList> optional = wishListRepository.findById(wishListId);
		return optional.get();
	}
}
