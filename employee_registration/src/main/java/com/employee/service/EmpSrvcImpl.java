package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.bean.EmployeeUpdate;
import com.employee.bean.Employeedto;
import com.employee.entity.Employee;
import com.employee.exception.NoNameResourceFoundException;
import com.employee.exception.NoPincodeResourceFoundException;
import com.employee.exception.NoSernameResourceFoundException;
import com.employee.exception.ResourceAlreadyExistException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.IEmployeeRepository;

@Service
public class EmpSrvcImpl implements IEmployeeService {

	@Autowired
	IEmployeeRepository iEmployeeRepository;
	
	Employee employee;
	Employeedto employeedto;
	List<Employee> employees;
	List<Employeedto> employeedtos;
	
	//new Employee
	@Transactional(readOnly = false)
	public Employeedto registerEmp(Employee employee) {
		if(iEmployeeRepository.existsByid(employee.getId())) {
			throw new ResourceAlreadyExistException("User "+employee.getId()+" already Existed");
		}
		employee = iEmployeeRepository.save(employee);
		return empTodto(employee);	
	}
	
	//get All employee
	@Transactional(readOnly = true)
	public List<Employeedto> getAllEmployee(){
		
		employees = iEmployeeRepository.findAll();
		return listEmpTodto(employees);
	}
	
	//get employee
	@Transactional(readOnly = true)
	public Employeedto getEmployee(int employeeId)  {	
		
		if(iEmployeeRepository.existsByid(employeeId))
		employee = iEmployeeRepository.findByid(employeeId);
		else
			throw new ResourceNotFoundException("given user "+employeeId+" not available");
		return empTodto(employee);
	}
	
	//update employee
	@Transactional(readOnly = false)
	public Employeedto updateEmployee(Employeedto employeedto) {
		
		if(iEmployeeRepository.existsByid(employeedto.getId())) {
			 employee = dtoToemp(employeedto, new Employee());
			 employee = iEmployeeRepository.save(employee);
		}
		else
			throw new ResourceNotFoundException("given user "+employeedto.getId()+" not available");
		return empTodto(employee);
	}
	
	//soft delete employee
	@Transactional(readOnly = false)
	public boolean deleteEmployee(int employeeId) throws ResourceNotFoundException {
		if(iEmployeeRepository.existsByid(employeeId))
     		iEmployeeRepository.deleteById(employeeId);
		else
			throw new ResourceNotFoundException("given user "+employeeId+" not available");
		return true;
	}
	
	//hard delete employee
	@Transactional(readOnly = false)
	public boolean hardDeleteEmployee(int employeeId) throws ResourceNotFoundException {
		if(iEmployeeRepository.existsByid(employeeId))
	    	iEmployeeRepository.hardDeleteByid(employeeId);
		else
			throw new ResourceNotFoundException("given user "+employeeId+" not available");
		return true;
	}
	
	//search by firstName
	@Transactional(readOnly = true)
	public List<Employeedto> searchByfirstName(String firstName){
		
		if(iEmployeeRepository.existsByfirstName(firstName))
	    	employees = iEmployeeRepository.findByfirstName(firstName);
		else
			throw new NoNameResourceFoundException("given users by Name "+firstName+" is not available");
		return listEmpTodto(employees);
	}
	
	//search by lastName
	@Transactional(readOnly = true)
	public List<Employeedto> searchBylastName(String lastName){
		
		if(iEmployeeRepository.existsBylastName(lastName))
	    	employees = iEmployeeRepository.findBylastName(lastName);
		else
			throw new NoSernameResourceFoundException("given users by Sername "+lastName+" is not available");
		return listEmpTodto(employees);
	}
	
	//search by pincode
	@Transactional(readOnly = true)
	public List<Employeedto> searchByPincode(int pincode){
		
		if(iEmployeeRepository.existsBypincode(pincode))
		    employees = iEmployeeRepository.findBypincode(pincode);
		else
			throw new NoPincodeResourceFoundException("given users by Pincode "+pincode+" is not available");
		return listEmpTodto(employees);
	}
	
	//sort by dob/doj
	@Transactional(readOnly = true)
	public List<Employeedto> sortByField(String field){
	
		employees = iEmployeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
		return listEmpTodto(employees);
	}
	
	//converting employee to dto
	public Employeedto empTodto(Employee employee) {
		
		employeedto = new Employeedto();
		employeedto.setFirstName(employee.getFirstName());
		employeedto.setLastName(employee.getLastName());
		employeedto.setId(employee.getId());
		employeedto.setPincode(employee.getPincode());
		employeedto.setCity(employee.getCity());
		employeedto.setBloodGroup(employee.getBloodGroup());
		employeedto.setAge(employee.getAge());
		employeedto.setDob(employee.getDob());
		employeedto.setDoj(employee.getDoj());
		employeedto.setDeleted(employee.isDeleted());
		return employeedto;
	}
	
	//converting List employee to List dto
	public List<Employeedto> listEmpTodto(List<Employee> employees){
	    List<Employeedto> employeedtos = new ArrayList<Employeedto>();
		return  employeedtos = employees.stream().map(emp -> {
			employeedto = new Employeedto();
			employeedto.setFirstName(emp.getFirstName());
			employeedto.setLastName(emp.getLastName());
			employeedto.setId(emp.getId());
			employeedto.setPincode(emp.getPincode());
			employeedto.setCity(emp.getCity());
			employeedto.setBloodGroup(emp.getBloodGroup());
			employeedto.setAge(emp.getAge());
			employeedto.setDob(emp.getDob());
			employeedto.setDoj(emp.getDoj());
			employeedto.setDeleted(emp.isDeleted());
		    return employeedto;
		}).collect(Collectors.toList());
	}
	
	//converting employeedto to employee
		public Employee dtoToemp(Employeedto employeedto, Employee employee) {

			employee.setFirstName(employeedto.getFirstName());
			employee.setLastName(employeedto.getLastName());
			employee.setId(employeedto.getId());
			employee.setPincode(employeedto.getPincode());
			employee.setCity(employeedto.getCity());
			employee.setBloodGroup(employeedto.getBloodGroup());
			employee.setAge(employeedto.getAge());
			employee.setDob(employeedto.getDob());
			employee.setDoj(employeedto.getDoj());
			employee.setDeleted(employeedto.isDeleted());
			return employee;
		}
}
