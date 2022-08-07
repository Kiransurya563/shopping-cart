package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface ProductService {

	ResponseStructure<Product> saveProduct(Product product);

	ResponseStructure<Product> updateProduct(Product product, int merchantId);

	ResponseStructure<Product> fetchProductById(int productId);

	Product findProductById(int productId);

	ResponseStructure<Product> deleteProduct(int productId);

	ResponseStructure<List<Product>> getAllProducts(int merchantId);
}
