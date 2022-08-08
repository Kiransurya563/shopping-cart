package com.jspiders.shoppingcart.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.ItemDao;
import com.jspiders.shoppingcart.dto.Item;
import com.jspiders.shoppingcart.repository.ItemRepository;

@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item deleteItem(Item item) {
		itemRepository.delete(item);
		return item;
	}

}
