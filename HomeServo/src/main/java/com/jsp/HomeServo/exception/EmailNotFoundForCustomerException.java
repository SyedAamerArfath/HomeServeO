package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class EmailNotFoundForCustomerException extends RuntimeException {
	private String message="email not exist please enter another one";

	public EmailNotFoundForCustomerException(String message) {
		super();
		this.message = message;
	}

	public EmailNotFoundForCustomerException() {
		super();
	}
	
	

}
