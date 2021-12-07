package com.employee.entity;

import java.util.Date;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="employee")
@Data
@SQLDelete(sql = "UPDATE Employee SET deleted=1 WHERE id=?")
@Where(clause = "deleted = 0")
//@FilterDef(name = "deletedEmployeeFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
//@Filter(name = "deletedEmployeeFilter", condition = "deleted = :isDeleted")
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
	//@Size(min = 6)
	private int pincode;
	@Column(name = "blood_group")
	@NotNull(message = "Bloodgroup should be filled")
	@NotEmpty(message = "Bloodgroup should be filled")
	private String bloodGroup;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doj;
	private boolean deleted = Boolean.FALSE;
}
