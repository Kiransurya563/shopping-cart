package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductDao {
	 Product saveProduct(Product product);

	

	 Optional<Product> findProductByid(int id);

	 void deleteProductById(int id);

     List<Product> getAllProducts();

}
