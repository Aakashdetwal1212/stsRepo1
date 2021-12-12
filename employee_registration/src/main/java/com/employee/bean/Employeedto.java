package com.employee.bean;

import java.sql.Date;
import java.time.LocalDate;

//import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
	//@JsonDeserialize(using = LocalDateDeserializer.class)  
	//@JsonSerialize(using = LocalDateSerializer.class) 
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	//@JsonFormat(pattern = "dd-MM-yyyy")
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private java.util.Date dob;
	//@JsonDeserialize(using = LocalDateDeserializer.class)  
	//@JsonSerialize(using = LocalDateSerializer.class) 
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
    //@JsonFormat(pattern = "dd-MM-yyyy")
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private java.util.Date doj;
	private boolean deleted;
	public Employeedto(
			@NotNull(message = "FirstName should be filled") @NotEmpty(message = "FirstName should be filled") String firstName,
			@NotNull(message = "lastName should be filled") @NotEmpty(message = "lastName should be filled") String lastName,
			@NotNull(message = "EmployeeId should be filled") int id,
			@Min(value = 18, message = "min age should be 18") int age,
			@NotNull(message = "city should be filled") @NotEmpty(message = "city should be filled") String city,
			@NotNull(message = "pincode should be filled") int pincode,
			@NotNull(message = "Bloodgroup should be filled") @NotEmpty(message = "Bloodgroup should be filled") String bloodGroup,
			java.util.Date dob, java.util.Date doj, boolean deleted) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.age = age;
		this.city = city;
		this.pincode = pincode;
		this.bloodGroup = bloodGroup;
		this.dob = dob;
		this.doj = doj;
		this.deleted = deleted;
	}
	
	public Employeedto() {
		// TODO Auto-generated constructor stub
	}
}
