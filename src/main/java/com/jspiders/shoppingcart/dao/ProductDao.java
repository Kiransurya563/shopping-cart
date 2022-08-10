package com.jspiders.shoppingcart.dao;

import java.util.List;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductDao {

	Product saveProduct(Product product);

	Product findProductByid(int productId);

	Product deleteProduct(Product product);

	List<Product> fetchAllProducts();

	Product findProductByName(String productName);
}
