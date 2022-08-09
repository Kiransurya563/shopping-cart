package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
