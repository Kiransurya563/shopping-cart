package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
