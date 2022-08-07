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
	ProductRepository productRepository ;
	public Product saveProduct(Product product) {
   	 return productRepository.save(product) ;
    }
   

	public Product updateProduct(Product Product) {
		return productRepository.save(Product);
	}

	public Optional<Product> findProductByid(int id) {
		return productRepository.findById(id);
	}

	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}
   
	public List<Product> getAllProducts() {
		return productRepository.findAll() ;
	}


}
