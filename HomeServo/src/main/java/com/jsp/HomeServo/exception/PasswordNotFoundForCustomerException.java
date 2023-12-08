package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class PasswordNotFoundForCustomerException extends RuntimeException {
	private String message="incorrect password please check the password again";

	public PasswordNotFoundForCustomerException() {
		super();
	}

	public PasswordNotFoundForCustomerException(String message) {
		super();
		this.message = message;
	}
	
	
}
