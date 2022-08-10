package com.jspiders.shoppingcart.dao;

import java.util.List;

import com.jspiders.shoppingcart.dto.Address;

public interface AddressDao {
	Address addAddress(Address address);

	Address removeAddress(Address address);

	List<Address> fetchCustomerAllAddresses();

	Address fetchAddressById(int addressId);
}
