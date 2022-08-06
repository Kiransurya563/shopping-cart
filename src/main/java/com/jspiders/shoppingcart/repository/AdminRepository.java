package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.shoppingcart.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
