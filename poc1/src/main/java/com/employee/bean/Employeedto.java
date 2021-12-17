package com.employee.bean;

import javax.persistence.Column;

//import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	//@NotNull(message = "pincode should be filled")
	private int pincode;
	
	@NotNull(message = "Bloodgroup should be filled")
	@NotEmpty(message = "Bloodgroup should be filled")
	private String bloodGroup;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private java.util.Date dateOfBirth;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "doj")
	private java.util.Date dateOfJoin;

	private boolean deleted;
	
	public Employeedto(
			@NotNull(message = "FirstName should be filled") @NotEmpty(message = "FirstName should be filled") String firstName,
			@NotNull(message = "lastName should be filled") @NotEmpty(message = "lastName should be filled") String lastName,
			@NotNull(message = "EmployeeId should be filled") int id,
			@Min(value = 18, message = "min age should be 18") int age,
			@NotNull(message = "city should be filled") @NotEmpty(message = "city should be filled") String city,
			@NotNull(message = "pincode should be filled") int pincode,
			@NotNull(message = "Bloodgroup should be filled") @NotEmpty(message = "Bloodgroup should be filled") String bloodGroup,
			java.util.Date dateOfBirth, java.util.Date dateOfJoin, boolean deleted) {   //NOSONAR
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.age = age;
		this.city = city;
		this.pincode = pincode;
		this.bloodGroup = bloodGroup;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoin = dateOfJoin;
		this.deleted = deleted;
	}
	
	public Employeedto() {
	}
}
