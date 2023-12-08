package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundForWork extends RuntimeException {
	private String message;

	public NoSuchElementFoundForWork(String message) {
		super();
		this.message = message;
	}
	

}
