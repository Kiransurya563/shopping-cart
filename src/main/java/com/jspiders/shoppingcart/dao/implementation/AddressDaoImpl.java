package com.jspiders.shoppingcart.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.shoppingcart.dao.AddressDao;
import com.jspiders.shoppingcart.dto.Address;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.repository.AddressRepository;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	AddressRepository addressRepository;

	@Override
	public Address addAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address removeAddress(Address address) {
		addressRepository.delete(address);
		return address;
	}

	@Override
	public List<Address> fetchCustomerAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address fetchAddressById(int addressId) {
		Optional<Address> optional = addressRepository.findById(addressId);
		try {
			return optional.get();
		} catch (Exception e) {
			throw new UserDefinedException("Could not find address, check address id");
		}
	}

}
