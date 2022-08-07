package com.jspiders.shoppingcart.dao;

import com.jspiders.shoppingcart.dto.Admin;

public interface AdminDao {

	Admin saveAdmin(Admin admin);

	Admin validateAdmin(String email, String password);
}
