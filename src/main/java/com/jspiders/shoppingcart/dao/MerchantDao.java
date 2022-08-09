package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Merchant;

public interface MerchantDao {

	Merchant saveMerchant(Merchant merchant);

	Merchant validateMerchant(String email, String Password);

	Optional<Merchant> findMerchantById(int merchantId);

	List<Merchant> fetchAllMerchants();

	Merchant deleteMerchant(Merchant merchant);

}
