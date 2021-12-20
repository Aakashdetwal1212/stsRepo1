package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeSrvcImpl;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeSrvcImpl empSrvcImpl;

	List<EmployeeDto> employeeDtos;
	EmployeeDto employeeDto;

	@PostMapping(value = "/addemp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody Employee employee) {
		employeeDto = empSrvcImpl.addEmployee(employee);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getallemp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
		employeeDtos = empSrvcImpl.getAllEmployee();
		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/getemp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int id) {
		employeeDto = empSrvcImpl.getEmployeeById(id);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	@PutMapping(value = "/updateemp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDto> updateEmployeeById(@Valid @RequestBody EmployeeDto employeeDto) {
		employeeDto = empSrvcImpl.updateEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteemp/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) {
		empSrvcImpl.deleteEmployeeById(id);
		return new ResponseEntity<String>("employee number" + id + " Deactivated", HttpStatus.OK);
	}

	@DeleteMapping(value = "/purgeemp/{id}")
	public ResponseEntity<String> hardDeleteEmployeeById(@PathVariable("id") int id) {
		empSrvcImpl.hardDeleteEmployeeById(id);
		return new ResponseEntity<String>("employee number " + id + " deleted", HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDto>> searchByfirstName(@PathVariable("firstName") String firstName) {
		employeeDtos = empSrvcImpl.searchByFirstName(firstName);
		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/surname/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDto>> searchBylastName(@PathVariable("lastName") String lastName) {
		employeeDtos = empSrvcImpl.searchByLastName(lastName);
		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/pincode/{pincode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDto>> searchByPincode(@PathVariable("pincode") int pincode) {
		employeeDtos = empSrvcImpl.searchByPincode(pincode);
		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/sort/{field}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDto>> sortByField(@PathVariable("field") String field) {
		employeeDtos = empSrvcImpl.sortByField(field);
		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
	}
}
