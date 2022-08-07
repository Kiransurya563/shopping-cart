package com.jspiders.shoppingcart.service;

import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface AdminService {

	public ResponseStructure<Admin> saveAdmin(Admin admin);

	public ResponseStructure<Admin> validateAdmin(String email, String password);

}
