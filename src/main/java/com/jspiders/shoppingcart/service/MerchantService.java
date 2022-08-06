package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface MerchantService {

	ResponseStructure<Merchant> createMerchant(Merchant merchant);

	ResponseStructure<Merchant> saveMerchant(String password, String token);

	ResponseStructure<Merchant> validateMerchant(String email, String Password);

	ResponseStructure<Merchant> findMerchantById(int id);

	ResponseStructure<List<Merchant>> fetchAllMerchant();
}
