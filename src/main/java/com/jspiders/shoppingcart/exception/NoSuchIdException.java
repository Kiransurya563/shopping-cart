package com.jspiders.shoppingcart.exception;

@SuppressWarnings("serial")
public class NoSuchIdException extends RuntimeException {
	private String message = "no one exists for given id";
	private int status;

	@Override
	public String getMessage() {
		return message;
	}

	public NoSuchIdException(int statuscode, String message) {
		status = statuscode;
		this.message = message;
	}

	public NoSuchIdException() {
	}

}
