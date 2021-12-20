package com.employee.bean;

import javax.persistence.Column;

//import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

	@NotBlank(message = "firstName can not be blank")
	private String firstName;
	
	@NotBlank(message = "lastName can not be blank")
	private String lastName;
	
	@NotNull(message = "employeeId can not be blank")
	private int id;
	@Min(value = 18, message = "min age should be 18")
	private int age;
	
	@NotBlank(message = "city can not be blank")
	private String city;
	private int pincode;
	
	@NotBlank(message = "bloodGroup can not be blank")
	private String bloodGroup;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private java.util.Date dateOfBirth;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "doj")
	private java.util.Date dateOfJoin;

	private boolean deleted;
	
	public EmployeeDto(
			@NotBlank(message = "firstName can not be blank") String firstName,
			@NotBlank(message = "lastName can not be blank") String lastName,
			@NotNull(message = "employeeId can not be blank") int id,
			@Min(value = 18, message = "minimum age should be 18") int age,
			@NotBlank(message = "city can not be blank") String city,
			int pincode,
			@NotBlank(message = "bloodGroup can not be blank") String bloodGroup,
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
	
	public EmployeeDto() {
	}
}
