package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Data
@SQLDelete(sql = "UPDATE Employee SET deleted=1 WHERE id=?")
@Where(clause = "deleted = 0")
public class Employee {             //false : not deleted, true : deleted

	@Id
	@Column(name = "e_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eId;
	
	@Column(name = "first_name")
	@NotNull(message = "FirstName should be filled")
	@NotEmpty(message = "FirstName should be filled")
	private String firstName;
	
	@Column(name= "last_name")
	@NotNull(message = "lastName should be filled")
	@NotEmpty(message = "lastName should be filled")
	private String lastName;
	
	@NotNull(message = "EmployeeId should be filled")
	private int id;
	
	@NotNull(message = "age should be filled")
	@Min(value = 18, message = "min age should be 18")
	private int age;
	
	@NotNull(message = "city should be filled")
	@NotEmpty(message = "city should be filled")
	private String city;
	
	//@NotBlank(message = "pincode cannot be blank")
	private int pincode;

	@Column(name = "blood_group")
	@NotNull(message = "Bloodgroup should be filled")
	@NotEmpty(message = "Bloodgroup should be filled")
	private String bloodGroup;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private java.util.Date dateOfBirth;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "doj")
	private java.util.Date dateOfJoin;
	
	private boolean deleted = Boolean.FALSE;
	
	public Employee(
			String firstName,
			String lastName,
			int id,
			int age,
			String city,
			int pincode,
		    String bloodGroup,
			java.util.Date dateOfBirth, java.util.Date dateOfJoin) { //NOSONAR
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
	}
	
	public Employee() {
	}
	
}
