package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundByVendorException extends RuntimeException {
	private String message;

	public NoSuchElementFoundByVendorException(String message) {
		super();
		this.message = message;
	}
	
}
