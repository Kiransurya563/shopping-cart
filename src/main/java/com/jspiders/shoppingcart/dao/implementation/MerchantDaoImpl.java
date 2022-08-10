package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.MerchantDao;
import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.repository.MerchantRepository;

@Repository
public class MerchantDaoImpl implements MerchantDao {
	@Autowired
	MerchantRepository merchantRepository;

	@Override
	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	@Override
	public Merchant validateMerchant(String email, String Password) {
		return merchantRepository.validateMerchant(email, Password);
	}

	@Override
	public Merchant findMerchantById(int merchantId) {
		Optional<Merchant> optional = merchantRepository.findById(merchantId);
		try {
			return optional.get();
		} catch (Exception e) {
			throw new UserDefinedException("Could not find merchant, check merchant id");
		}
	}

	@Override
	public List<Merchant> fetchAllMerchants() {
		return merchantRepository.findAll();
	}

	@Override
	public Merchant deleteMerchant(Merchant merchant) {
		merchantRepository.delete(merchant);
		return merchant;
	}
}
