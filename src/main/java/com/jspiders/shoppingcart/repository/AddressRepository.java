package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
