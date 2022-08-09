package com.jspiders.shoppingcart.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dao.AddressDao;
import com.jspiders.shoppingcart.dao.CustomerDao;
import com.jspiders.shoppingcart.dto.Address;
import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.exception.UserDefinedException;
import com.jspiders.shoppingcart.helper.ResponseStructure;
import com.jspiders.shoppingcart.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	AddressDao addressDao;

	@Override
	public ResponseStructure<Address> addAddress(Address address, int customerId) {

		Optional<Customer> opCustomer = customerDao.findCustomerById(customerId);

		if (opCustomer.isEmpty()) {
			throw new UserDefinedException("Customer Id did not match any");
		} else {
			Customer customer = opCustomer.get();
			List<Address> list = customer.getAddresses();
			list.add(address);
			address = addressDao.addAddress(address);
			customer.setAddresses(list);

			customerDao.saveCustomer(customer);
		}
		ResponseStructure<Address> structure = new ResponseStructure<Address>();
		structure.setData(address);
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Address added Succesfully.");
		return structure;
	}

	@Override
	public ResponseStructure<Address> removeAddress(int addressId) {
		Optional<Address> optional = addressDao.fetchAddressById(addressId);
		if (optional.isEmpty()) {
			throw new UserDefinedException("Address Id did not match any");
		} else {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setData(addressDao.removeAddress(optional.get()));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Address removed Succesfully.");
			return structure;
		}

	}

	@Override
	public ResponseStructure<List<Address>> fetchCustomerAllAddresses(int customerId) {
		Optional<Customer> opCustomer = customerDao.findCustomerById(customerId);

		if (opCustomer.isEmpty()) {
			throw new UserDefinedException("Customer Id did not match any");
		} else {
			Customer customer = opCustomer.get();
			List<Address> list = customer.getAddresses();

			ResponseStructure<List<Address>> structure = new ResponseStructure<List<Address>>();
			structure.setData(list);
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Address Found.");
			return structure;
		}
	}

	@Override
	public ResponseStructure<Address> fetchAddressById(int addressId) {
		Optional<Address> optional = addressDao.fetchAddressById(addressId);
		if (optional.isEmpty()) {
			throw new UserDefinedException("Address Id did not match any");
		} else {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setData(optional.get());
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Address Found.");
			return structure;
		}
	}

}
