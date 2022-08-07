package com.jspiders.shoppingcart.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.jspiders.shoppingcart.dao.MerchantDao;
import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.exception.InvalidCredentialsException;
import com.jspiders.shoppingcart.exception.NoSuchIdException;
import com.jspiders.shoppingcart.helper.ResponseStructure;

import com.jspiders.shoppingcart.service.ProductService;

public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;

	@Autowired
	MerchantDao merchantDao ;

	public ResponseStructure<Product> saveProduct(Product product) {
		if (product != null) {
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("saved");
			responseStructure.setData(productDao.saveProduct(product));
			return responseStructure;
		} else {
			throw new InvalidCredentialsException(601, "data is null");
		}
	}

	public ResponseStructure<Product> updateProduct(Product product, int id) {
		if (productDao.findProductByid(product.getId()).isEmpty()) {
			throw new NoSuchIdException(602, "no one exist for given id to update");
		} else {
			Merchant merchant = merchantDao.findMerchantById(id).get();
			product.setMerchant(merchant);
			productDao.updateProduct(product);
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(product);
			return responseStructure;
		}
	}

	public ResponseStructure<Product> fetchProductById(int id) {
		if (productDao.findProductByid(id).isEmpty()) {
			throw new NoSuchIdException(602, "no one exist for given id to fetch");
		} else {
			Product Product = productDao.findProductByid(id).get();
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(Product);
			return responseStructure;
		}
	}

	public Product findProductById(int id) {
		if (productDao.findProductByid(id).isEmpty()) {
			throw new NoSuchIdException(602, "no one exist for given id to fetch");
		} else {
			Product Product = productDao.findProductByid(id).get();
			return Product;
		}
	}

	public ResponseStructure<Product> deleteProduct(int id) {
		if (productDao.findProductByid(id).isEmpty()) {
			throw new NoSuchIdException(602, "no one exist for given id to delete");
		} else {
			productDao.deleteProductById(id);
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("successfully deleted");
			return responseStructure;
		}
	}

	public ResponseStructure<List<Product>> getAllProducts(int id) {
		Merchant merchant = merchantDao.findMerchantById(id).get() ;
		if(merchant!=null) {
		List<Product> products = merchant.getProducts();
		if (products.size() > 0) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("merchant's products");
			return responseStructure;
		} else {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();

			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("no product for this merchant");
			return responseStructure;
		}
		}
		else {
			throw new NoSuchIdException() ;
		}
			
	}

}
