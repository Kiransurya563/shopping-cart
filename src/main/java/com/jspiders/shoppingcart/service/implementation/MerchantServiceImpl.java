package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.implementation.MerchantDaoImpl;
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.MerchantMailVerification;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.MerchantService;

import net.bytebuddy.utility.RandomString;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	MerchantDaoImpl merchantDaoImpl;

	@Autowired
	MerchantMailVerification merchantMailVerification;

	Merchant merchant;

	public ResponseStructure<Merchant> createMerchant(Merchant merchant) {
		String token = RandomString.make(32);
		merchant.setToken(token);
		merchant.setStatus("inactive");
		this.merchant = merchant;

		merchantMailVerification.SendVerificationEmail(merchant);

		ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();

		structure.setData(merchant);
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Check mail for verification link");
		return structure;
	}

	public ResponseStructure<Merchant> saveMerchant(String token, String password) {
		if (token.equals(merchant.getToken())) {
			merchant.setPassword(password);
			merchant.setStatus("pending");
			merchant.setToken(null);

			Merchant merchant1 = merchantDaoImpl.saveMerchant(merchant);

			ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
			if (merchant1 != null) {
				structure.setData(merchant1);
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage(
						"Account Registered succesfully, please wait for 24 hours to get account activated");
				return structure;
			} else {
				throw new UserDefinedException("Account did not get Created");
			}
		} else {
			throw new UserDefinedException("Token mismatch check your link and try again");
		}
	}

	public ResponseStructure<Merchant> validateMerchant(String email, String password) {
		Merchant merchant = merchantDaoImpl.validateMerchant(email, password);
		if (merchant == null) {
			throw new UserDefinedException("Login Failed, check email and password and try again");
		} else {
			if (merchant.getStatus().equals("active")) {
				ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
				structure.setData(merchant);
				structure.setMessage("Login Succesfull");
				structure.setStatusCode(HttpStatus.FOUND.value());
				return structure;
			} else {
				throw new UserDefinedException("Your account activation process is Pending please contact admin");
			}
		}
	}

	@Override
	public ResponseStructure<Merchant> findMerchantById(int merchantId) {
		Optional<Merchant> merchant1 = merchantDaoImpl.findMerchantById(merchantId);
		if (merchant1.isEmpty()) {
			throw new UserDefinedException("Couldnt find Merchant with the id " + merchantId);
		} else {
			ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
			structure.setData(merchant1.get());
			structure.setMessage("Data found...");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return structure;
		}
	}

	@Override
	public ResponseStructure<List<Merchant>> fetchAllMerchant() {
		List<Merchant> list = merchantDaoImpl.fetchAllMerchants();
		if (list.isEmpty()) {
			throw new UserDefinedException("No data found...");
		} else {
			ResponseStructure<List<Merchant>> structure = new ResponseStructure<List<Merchant>>();
			structure.setData(list);
			structure.setMessage("Data found...");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return structure;
		}
	}

	@Override
	public ResponseStructure<List<Merchant>> changeMerchantStatus(int merchantId) {
		ResponseStructure<Merchant> merchant1 = findMerchantById(merchantId);
		Merchant merchant2 = merchant1.getData();
		if (merchant2.getStatus().equals("pending")) {
			merchant2.setStatus("active");
			merchantDaoImpl.saveMerchant(merchant2);
		} else if (merchant2.getStatus().equals("active")) {
			merchant2.setStatus("inactive");
			merchantDaoImpl.saveMerchant(merchant2);
		}
		return fetchAllMerchant();
	}

}
