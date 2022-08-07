package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Product;

public interface ProductDao {
	public Product saveProduct(Product product) ;
   

	public Product updateProduct(Product Product) ;

	public Optional<Product> findProductByid(int id) ;

	public void deleteProductById(int id)  ;
   
	public List<Product> getAllProducts()  ;


}
