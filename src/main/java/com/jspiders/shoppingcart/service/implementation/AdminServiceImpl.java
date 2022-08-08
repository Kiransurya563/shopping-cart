package com.jspiders.shoppingcart.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.AdminDao;
import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public ResponseStructure<Admin> saveAdmin(Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(adminDao.saveAdmin(admin));
		return responseStructure;
	}

	@Override
	public ResponseStructure<Admin> validateAdmin(String email, String password) {
		Admin admin = adminDao.validateAdmin(email, password);
		if (admin != null) {
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Valid Admin");
			responseStructure.setData(admin);
			return responseStructure;
		} else {
			throw new UserDefinedException("Invalid Admin");
		}
	}

}
