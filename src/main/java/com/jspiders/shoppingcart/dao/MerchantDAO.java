package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Merchant;

public interface MerchantDAO {

	Merchant saveMerchant(Merchant merchant);

	Merchant validateMerchant(String email, String Password);

	Optional<Merchant> findMerchantById(int id);

	List<Merchant> fetchAllMerchants();
}
