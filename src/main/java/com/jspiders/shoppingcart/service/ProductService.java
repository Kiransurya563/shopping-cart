package com.jspiders.shoppingcart.service;

import java.util.List;


import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface ProductService {
	

	public ResponseStructure<Product> saveProduct(Product product)  ;

	public ResponseStructure<Product> updateProduct(Product product, int id) ;
	public ResponseStructure<Product> fetchProductById(int id)  ;

	public Product findProductById(int id)  ;
	public ResponseStructure<Product> deleteProduct(int id)  ;

	public ResponseStructure<List<Product>> getAllProducts(int id)  ;
}
