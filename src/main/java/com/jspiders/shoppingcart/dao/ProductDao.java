package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductDao {

	Product saveProduct(Product product);

	Optional<Product> findProductByid(int productId);

	Product deleteProduct(Product product);

	List<Product> fetchAllProducts();
}
