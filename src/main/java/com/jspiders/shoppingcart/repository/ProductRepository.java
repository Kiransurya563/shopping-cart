package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findProductByName(String productName);

}
