package com.jspiders.shoppingcart.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.AdminDao;
import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.repository.AdminRepository;

@Repository
public class AdminDaoImpl implements AdminDao {
	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin validateAdmin(String email, String password) {
		return adminRepository.validateAdmin(email, password);
	}
}
