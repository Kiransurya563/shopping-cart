package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Admin;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.dto.Merchant;

public interface AdminDao {

	public Admin saveAdmin(Admin admin);
	public Admin validateAdmin(String email, String password);
	public List<Merchant> fetchMerchants();
	public List<Customer> fetchCustomers();
	public Optional<Merchant> findMerchantById(int merchantId);
	public Optional<Customer> findCustomerById(int customerId);
	
}
