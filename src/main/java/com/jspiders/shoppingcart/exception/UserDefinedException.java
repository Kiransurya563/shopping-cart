package com.jspiders.shoppingcart.exception;

@SuppressWarnings("serial")
public class UserDefinedException extends RuntimeException {
	private String message = "";

	public UserDefinedException() {
	}

	public UserDefinedException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
