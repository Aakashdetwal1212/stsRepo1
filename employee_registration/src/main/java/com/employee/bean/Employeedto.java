package com.employee.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Employeedto {

	@NotNull(message = "FirstName should be filled")
	@NotEmpty(message = "FirstName should be filled")
	private String firstName;
	@NotNull(message = "lastName should be filled")
	@NotEmpty(message = "lastName should be filled")
	private String lastName;
	@NotNull(message = "EmployeeId should be filled")
	private int id;
	@Min(value = 18, message = "min age should be 18")
	private int age;
	@NotNull(message = "city should be filled")
	@NotEmpty(message = "city should be filled")
	private String city;
	@NotNull(message = "pincode should be filled")
	private int pincode;
	@NotNull(message = "Bloodgroup should be filled")
	@NotEmpty(message = "Bloodgroup should be filled")
	private String bloodGroup;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doj;
	private boolean deleted;
}
