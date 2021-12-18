package com.employee.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter  
public class ErrorDetails {

	private Date timeStamp;
	private String message;
	private String details;

	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	
}
