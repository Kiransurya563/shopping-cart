package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

}
