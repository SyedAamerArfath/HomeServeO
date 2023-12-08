package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundFOrAddressException extends RuntimeException {
	
	private String message;

	public NoSuchElementFoundFOrAddressException(String message) {
		super();
		this.message = message;
	}
	
}
