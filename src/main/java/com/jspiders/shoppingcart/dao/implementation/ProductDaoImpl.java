package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product deleteProduct(Product product) {
		productRepository.delete(product);
		return product;
	}

	@Override
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductByid(int productId) {
		Optional<Product> prodOptional = productRepository.findById(productId);
		return prodOptional.get();
	}

}
