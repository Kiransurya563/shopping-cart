package com.jspiders.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jspiders.shoppingcart.helper.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = UserDefinedException.class)
	public ResponseEntity<ResponseStructure<String>> userDefinedExceptionHandler(UserDefinedException ie) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("Request failed");
		responseStructure.setData(ie.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> invalidCredentialsException(InvalidCredentialsException ie) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(ie.getMessage());
		responseStructure.setMessage("invalid credentials");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoSuchIdException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchIdException(NoSuchIdException ie) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(ie.getMessage());
		responseStructure.setMessage("no one exists for that id");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
}
