package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
