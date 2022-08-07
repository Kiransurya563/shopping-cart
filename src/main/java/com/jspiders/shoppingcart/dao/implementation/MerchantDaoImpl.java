package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.MerchantDao;
import com.jspiders.shoppingcart.dto.Merchant;
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
		return validateMerchant(email, Password);
	}

	@Override
	public Optional<Merchant> findMerchantById(int merchantId) {
		return merchantRepository.findById(merchantId);
	}

	@Override
	public List<Merchant> fetchAllMerchants() {
		return merchantRepository.findAll();
	}

}
