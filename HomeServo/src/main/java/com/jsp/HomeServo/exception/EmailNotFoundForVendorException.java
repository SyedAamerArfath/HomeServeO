package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class EmailNotFoundForVendorException extends RuntimeException {
	private String message;

	public EmailNotFoundForVendorException(String message) {
		super();
		this.message = message;
	}
	
}
