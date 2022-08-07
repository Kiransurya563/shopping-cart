package com.jspiders.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspiders.shoppingcart.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select data from Admin data where email=?1 and password=?2")
	Admin validateAdmin(String email, String password);

}
