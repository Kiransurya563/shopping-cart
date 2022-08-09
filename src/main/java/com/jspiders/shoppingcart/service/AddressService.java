package com.jspiders.shoppingcart.service;

import java.util.List;

import com.jspiders.shoppingcart.dto.Address;
import com.jspiders.shoppingcart.helper.ResponseStructure;

public interface AddressService {
	ResponseStructure<Address> addAddress(Address address, int customerId);

	ResponseStructure<Address> removeAddress(int addressId);

	ResponseStructure<List<Address>> fetchCustomerAllAddresses(int customerId);

	ResponseStructure<Address> fetchAddressById(int addressId);
}
