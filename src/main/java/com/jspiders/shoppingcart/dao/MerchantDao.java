package com.jspiders.shoppingcart.dao;

import java.util.List;

import com.jspiders.shoppingcart.dto.Merchant;

public interface MerchantDao {

	Merchant saveMerchant(Merchant merchant);

	Merchant validateMerchant(String email, String Password);

	Merchant findMerchantById(int merchantId);

	List<Merchant> fetchAllMerchants();

	Merchant deleteMerchant(Merchant merchant);

}
