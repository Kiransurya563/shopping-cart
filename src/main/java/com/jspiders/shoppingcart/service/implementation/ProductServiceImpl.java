package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.Optional;
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
		Optional<Merchant> merchant = merchantDao.findMerchantById(merchantId);
		if (merchant.isPresent()) {
			Merchant merchant1 = merchant.get();
			product.setMerchant(merchant1);
			List<Product> list = merchant1.getProducts();
			list.add(product);
			merchant1.setProducts(list);
			merchantDao.saveMerchant(merchant1);

			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Product Added");
			responseStructure.setData(productDao.saveProduct(product));
			return responseStructure;

		} else {
			throw new UserDefinedException("Invalid merchant id");
		}
	}

	@Override
	public ResponseStructure<List<Product>> fetchMerchantAllProducts(int merchantId) {
		Optional<Merchant> merchant = merchantDao.findMerchantById(merchantId);
		if (merchant.isEmpty()) {
			throw new UserDefinedException("Invalid merchant Id");
		} else {
			Merchant merchant1 = merchant.get();
			List<Product> products = merchant1.getProducts();
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
	}

	@Override
	public ResponseStructure<Product> fetchProductById(int productId) {
		Optional<Product> product = productDao.findProductByid(productId);
		if (product.isEmpty()) {
			throw new UserDefinedException("No product with the id " + productId);
		} else {
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Found");
			responseStructure.setData(product.get());
			return responseStructure;
		}
	}

	public ResponseStructure<List<Product>> updateProduct(Product product, int merchantId) {
		Optional<Merchant> merchant = merchantDao.findMerchantById(merchantId);
		if (merchant.isPresent()) {
			Merchant merchant1 = merchant.get();
			if (product.getId() == 0) {
				throw new UserDefinedException("Invalid product id");
			} else {
				product.setMerchant(merchant1);
				productDao.saveProduct(product);
			}
			return fetchMerchantAllProducts(merchantId);

		} else {
			throw new UserDefinedException("Invalid merchant id");
		}

	}

	public ResponseStructure<List<Product>> deleteProduct(int productId) {
		Optional<Product> product = productDao.findProductByid(productId);
		if (product.isEmpty()) {
			throw new UserDefinedException("No product with the id " + productId);
		} else {
			productDao.deleteProduct(product.get());
			int merchantId = product.get().getMerchant().getId();
			return fetchMerchantAllProducts(merchantId);
		}

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
