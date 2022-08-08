package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspiders.shoppingcart.dto.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

	@Query("select data from Merchant data where email=?1 and password=?2")
	Merchant validateMerchant(String email, String password);
}
