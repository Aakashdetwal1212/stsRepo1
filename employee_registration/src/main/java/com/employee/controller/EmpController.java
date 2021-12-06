package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmployeeUpdate;
import com.employee.bean.Employeedto;
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
	
	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employeedto> getAllEmp(){
		return empSrvcImpl.getAllEmployee();
	}
	
	@GetMapping(value = "/getemp/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employeedto getEmp(@PathVariable("employeeId") int employeeId) {
		return empSrvcImpl.getEmployee(employeeId);
	} 
	
	@PutMapping(value = "/updateemp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employeedto updateEmployee(@Valid @RequestBody Employeedto employeedto) {
		return empSrvcImpl.updateEmployee(employeedto);
	}
	
	//hard delete
	@DeleteMapping(value = "/deleteemp/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
		empSrvcImpl.deleteEmployee(employeeId);
		return "emp number"+employeeId+" deleted";
	}
	
	//public String deleteEmp(@PathVariable("employeeId") String employeeId) {
		
	//}
}
