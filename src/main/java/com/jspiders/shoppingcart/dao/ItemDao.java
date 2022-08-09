package com.jspiders.shoppingcart.dao;

import com.jspiders.shoppingcart.dto.Item;

public interface ItemDao {

	Item saveItem(Item item);

	Item deleteItem(Item item);
}
