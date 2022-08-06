package com.jspiders.shoppingcart.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private long phone;
	private String gender;
	private String token;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
	private List<Order> orders;
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Address> addresses;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
	private List<WishList> wishLists;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Cart cart;
}
