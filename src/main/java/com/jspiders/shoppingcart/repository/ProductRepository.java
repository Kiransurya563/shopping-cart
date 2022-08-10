package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select data from Product data where name=:productName")
	Product findProductByName(String productName);

}
