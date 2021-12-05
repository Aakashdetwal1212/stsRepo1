package com.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmpSrvcImpl;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	EmpSrvcImpl empSrvcImpl;
	
	@PostMapping(value = "/new",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee newEmp(@Valid @RequestBody Employee employee) {
		
		Employee employee2 = empSrvcImpl.registerEmp(employee);
			return employee2;
	}
}
