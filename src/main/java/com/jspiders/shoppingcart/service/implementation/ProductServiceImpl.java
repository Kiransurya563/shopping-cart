package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.MerchantDao;
import com.jspiders.shoppingcart.dao.ProductDao;
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.dto.Product;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Autowired
	MerchantDao merchantDao;

	@Override
	public ResponseStructure<Product> saveProduct(Product product, int merchantId) {
		Merchant merchant = merchantDao.findMerchantById(merchantId);

		product.setMerchant(merchant);
		List<Product> list = merchant.getProducts();
		list.add(product);
		merchant.setProducts(list);
		merchantDao.saveMerchant(merchant);

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product Added");
		responseStructure.setData(productDao.saveProduct(product));
		return responseStructure;

	}

	@Override
	public ResponseStructure<List<Product>> fetchMerchantAllProducts(int merchantId) {
		Merchant merchant = merchantDao.findMerchantById(merchantId);
		List<Product> products = merchant.getProducts();
		if (products.size() > 0) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("merchant's products");
			return responseStructure;
		} else {
			throw new UserDefinedException("No products found ");
		}
	}

	@Override
	public ResponseStructure<Product> fetchProductById(int productId) {
		Product product = productDao.findProductByid(productId);

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Product Found");
		responseStructure.setData(product);
		return responseStructure;
	}

	public ResponseStructure<List<Product>> updateProduct(Product product, int merchantId) {
		Merchant merchant = merchantDao.findMerchantById(merchantId);

		if (product.getId() == 0) {
			throw new UserDefinedException("Invalid product id");
		} else {
			product.setMerchant(merchant);
			productDao.saveProduct(product);
		}
		return fetchMerchantAllProducts(merchantId);

	}

	public ResponseStructure<List<Product>> deleteProduct(int productId) {
		Product product = productDao.findProductByid(productId);

		int merchantId = product.getMerchant().getId();
		productDao.deleteProduct(product);
		return fetchMerchantAllProducts(merchantId);
	}

	@Override
	public ResponseStructure<List<Product>> fetchAllProducts() {
		List<Product> products = productDao.fetchAllProducts().stream()
				.filter(p -> p.getMerchant().getStatus().equals("active")).collect(Collectors.toList());
		ResponseStructure<List<Product>> structure = new ResponseStructure<List<Product>>();
		structure.setData(products);
		structure.setMessage("Data found");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return structure;
	}
}