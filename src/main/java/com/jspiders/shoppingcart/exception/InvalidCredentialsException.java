package com.jspiders.shoppingcart.exception;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class InvalidCredentialsException extends RuntimeException{
	 private String msg = "invalid credentials" ;
	    private int status ;
	    public InvalidCredentialsException(int statusCode, String message) {
	    	msg = message ;
	    	status = statusCode ;
	    }
	    @Override
	    public String getMessage() {
	    	return msg ;
	    }
	   public InvalidCredentialsException() {}
}
