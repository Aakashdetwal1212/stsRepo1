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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
@SQLDelete(sql = "UPDATE Employee SET deleted=1 WHERE id=?")
@Where(clause = "deleted = 0")
public class Employee {             //false : not deleted, true : deleted

	@Id
	@Column(name = "e_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eId;
	
	@Column(name = "first_name")
	@NotBlank(message = "firstName can not be blank")
	private String firstName;
	
	@Column(name= "last_name")
	@NotBlank(message = "firstName can not be blank")
	private String lastName;
	
	@NotNull(message = "EmployeeId can not be blank")
	private int id;
	
	@Min(value = 18, message = "min Age should be 18")
	private int age;
	
	@NotBlank(message = "City can not be blank")
	private String city;
	private int pincode;

	@Column(name = "blood_group")
	@NotBlank(message = "bloodGroup can not be blank")
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
