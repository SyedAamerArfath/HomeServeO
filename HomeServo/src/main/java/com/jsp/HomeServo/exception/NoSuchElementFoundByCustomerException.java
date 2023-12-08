package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundByCustomerException extends RuntimeException {
 private String message;

public NoSuchElementFoundByCustomerException(String message) {
	super();
	this.message = message;
}
 
 
}
