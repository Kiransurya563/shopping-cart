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

}
