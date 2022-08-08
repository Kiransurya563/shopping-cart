package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface ProductService {

	ResponseStructure<Product> saveProduct(Product product, int merchantId);

	ResponseStructure<List<Product>> fetchMerchantAllProducts(int merchantId);

	ResponseStructure<Product> fetchProductById(int productId);

	ResponseStructure<List<Product>> updateProduct(Product product, int merchantId);

	ResponseStructure<List<Product>> deleteProduct(int productId);

	ResponseStructure<List<Product>> fetchAllProducts();

}
