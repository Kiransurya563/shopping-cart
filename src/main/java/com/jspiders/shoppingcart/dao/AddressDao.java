package com.jspiders.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import com.jspiders.shoppingcart.dto.Address;

public interface AddressDao {
	Address addAddress(Address address);

	Address removeAddress(Address address);

	List<Address> fetchCustomerAllAddresses();

	Optional<Address> fetchAddressById(int addressId);
}
