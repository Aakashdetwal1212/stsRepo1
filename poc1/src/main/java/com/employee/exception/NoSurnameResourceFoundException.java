package com.employee.exception;

public class NoSurnameResourceFoundException extends RuntimeException {

	public NoSurnameResourceFoundException(String str) {
		super(str);
	}
}
