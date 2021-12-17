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
import com.employee.service.EmpSrvcImpl;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	EmpSrvcImpl empSrvcImpl;
	
	List<Employeedto> employeedtos;
	
	@PostMapping(value = "/new",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> newEmp(@Valid @RequestBody Employee employee) {
		
		Employeedto employeedto = empSrvcImpl.registerEmp(employee);
		return new ResponseEntity<Employeedto>(employeedto,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> getAllEmp(){
		List<Employeedto> employeedtos;
		employeedtos = empSrvcImpl.getAllEmployee();
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getemp/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> getEmp(@PathVariable("employeeId") int employeeId) {
		Employeedto employeedto;
		employeedto = empSrvcImpl.getEmployee(employeeId);
		return new ResponseEntity<Employeedto>(employeedto, HttpStatus.FOUND);
	} 
	
	@PutMapping(value = "/updateemp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employeedto> updateEmployee(@Valid @RequestBody Employeedto employeedto) {
		Employeedto employeedto2;
		employeedto2 = empSrvcImpl.updateEmployee(employeedto);
		return new ResponseEntity<Employeedto>(employeedto2, HttpStatus.OK);
	}
	
	//soft delete
	@DeleteMapping(value = "/deleteemp/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int employeeId) {
		empSrvcImpl.deleteEmployee(employeeId);
		return new ResponseEntity<String>("emp number"+employeeId+" Deactivated",HttpStatus.OK);
	}
	
	//hard delete
	@DeleteMapping(value = "/hdelete/{employeeId}")
	public ResponseEntity<String> hardDeleteEmployee(@PathVariable("employeeId") int employeeId) {
		empSrvcImpl.hardDeleteEmployee(employeeId);
		return new ResponseEntity<String>("emp number "+employeeId+" deleted",HttpStatus.OK);
	}
	
	//search by firstName
	@GetMapping(value = "/searchbyname/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> searchByfirstName(@PathVariable("firstName") String firstName){
		employeedtos = empSrvcImpl.searchByfirstName(firstName);
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
	}
	
	//search by lastName
    @GetMapping(value = "/searchbysername/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employeedto>> searchBylastName(@PathVariable("lastName") String lastName){
		employeedtos = empSrvcImpl.searchBylastName(lastName);
		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
	}
    
    //search by pincode
  	@GetMapping(value = "/searchbypincode/{pincode}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<List<Employeedto>> searchByPincode(@PathVariable("pincode") int pincode){
  		employeedtos = empSrvcImpl.searchByPincode(pincode);
  		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
  	}
  	
  	//sort by dob/doj
  	@GetMapping(value = "/sort/{field}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<List<Employeedto>> sortByField(@PathVariable("field") String field) {
  		employeedtos = empSrvcImpl.sortByField(field);
  		return new ResponseEntity<List<Employeedto>>(employeedtos,HttpStatus.FOUND);
  	}
}
