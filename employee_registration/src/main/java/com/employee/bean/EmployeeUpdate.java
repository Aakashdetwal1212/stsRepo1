package com.employee.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeUpdate {

	@NotNull(message = "FirstName should be filled")
	@NotEmpty(message = "FirstName should be filled")
	private String firstName;
	@NotNull(message = "lastName should be filled")
	@NotEmpty(message = "lastName should be filled")
	private String lastName;
	@Min(value = 18, message = "min age should be 18")
	private int age;
	@NotNull(message = "EmployeeId should be filled")
	@NotEmpty(message = "EmployeeId should be filled")
	private String employeeId;
	@NotNull(message = "city should be filled")
	@NotEmpty(message = "city should be filled")
	private String city;
	@Size(min = 6)
	private int pincode;
	@NotNull(message = "Bloodgroup should be filled")
	@NotEmpty(message = "Bloodgroup should be filled")
	private String bloodGroup;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doj;
}
