package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspiders.shoppingcart.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select data from Customer data where email=?1 and password=?2")
	Customer validateCustomer(String email, String password);

}
