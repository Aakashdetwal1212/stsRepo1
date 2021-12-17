package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.Employeedto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeSrvcImpl;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeSrvcImpl empSrvcImpl;
	
	List<Employeedto> employeedtos;
	Employeedto employeedto;
	
	@PostMapping(value = "/addemp",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> addEmployee(@Valid @RequestBody Employee employee) {
		employeedto = empSrvcImpl.addEmployee(employee);
		return new ResponseEntity<Employeedto>(employeedto,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getallemp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> getAllEmployee(){
		employeedtos = empSrvcImpl.getAllEmployee();
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getemp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> getEmployeeById(@PathVariable("id") int id) {
		employeedto = empSrvcImpl.getEmployeeById(id);
		return new ResponseEntity<Employeedto>(employeedto, HttpStatus.FOUND);
	} 
	
	@PutMapping(value = "/updateemp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> updateEmployeeById(@Valid @RequestBody Employeedto employeedto) {
		employeedto = empSrvcImpl.updateEmployee(employeedto);
		return new ResponseEntity<Employeedto>(employeedto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteemp/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) {
		empSrvcImpl.deleteEmployeeById(id);
		return new ResponseEntity<String>("employee number"+id+" Deactivated",HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/purgeemp/{id}")
	public ResponseEntity<String> hardDeleteEmployeeById(@PathVariable("id") int id) {
		empSrvcImpl.hardDeleteEmployeeById(id);
		return new ResponseEntity<String>("employee number "+id+" deleted",HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> searchByfirstName(@PathVariable("firstName") String firstName){
		employeedtos = empSrvcImpl.searchByfirstName(firstName);
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
	}
	
    @GetMapping(value = "/surname/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> searchBylastName(@PathVariable("lastName") String lastName){
		employeedtos = empSrvcImpl.searchBylastName(lastName);
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
	}

  	@GetMapping(value = "/pincode/{pincode}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<List<Employeedto>> searchByPincode(@PathVariable("pincode") int pincode){
  		employeedtos = empSrvcImpl.searchByPincode(pincode);
  		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
  	}
  	
  	@GetMapping(value = "/sort/{field}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<List<Employeedto>> sortByField(@PathVariable("field") String field) {
  		employeedtos = empSrvcImpl.sortByField(field);
  		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
  	}
}
