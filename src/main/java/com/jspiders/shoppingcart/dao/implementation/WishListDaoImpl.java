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
	public Optional<WishList> findWishListById(int wishListId) {
		return wishListRepository.findById(wishListId);
	}

}
