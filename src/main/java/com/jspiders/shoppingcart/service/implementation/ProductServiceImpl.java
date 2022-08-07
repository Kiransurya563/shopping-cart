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

public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Autowired
	MerchantDao merchantDao;

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

	public ResponseStructure<List<Product>> updateProduct(Product product, int merchantId) {
		if (productDao.findProductByid(product.getId()).isEmpty()) {
			throw new NoSuchIdException(602, "no product exist for given id to update");
		} else {
			if (merchantDao.findMerchantById(merchantId).isPresent()) {
				Merchant merchant = merchantDao.findMerchantById(merchantId).get();
				List<Product> products = merchant.getProducts();
				product.setMerchant(merchant);
				productDao.saveProduct(product);
				
				return getAllProducts(merchantId) ;
			}
		}
	}

	public ResponseStructure<Product> fetchProductById(int productId) {
		if (productDao.findProductByid(productId).isEmpty()) {
			throw new NoSuchIdException(602, "no product exist for given id to fetch");
		} else {
			Product Product = productDao.findProductByid(productId).get();
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(Product);
			return responseStructure;
		}
	}

	public Product findProductById(int productId) {
		if (productDao.findProductByid(productId).isEmpty()) {
			throw new NoSuchIdException(602, "no product exist for given id to fetch");
		} else {
			Product Product = productDao.findProductByid(productId).get();
			return Product;
		}
	}

	public ResponseStructure<Product> deleteProduct(int productId) {
		if (productDao.findProductByid(productId).isEmpty()) {
			throw new NoSuchIdException(602, "no product exist for given id to delete");
		} else {
			productDao.deleteProductById(productId);
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("successfully deleted");
			return responseStructure;
		}
	}

	public ResponseStructure<List<Product>> getAllProducts(int merchantId) {
		Merchant merchant = merchantDao.findMerchantById(merchantId).get();
		if (merchant != null) {
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
		} else {
			throw new NoSuchIdException();
		}

	}

}
