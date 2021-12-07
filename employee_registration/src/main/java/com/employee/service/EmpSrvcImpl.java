package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.bean.EmployeeUpdate;
import com.employee.bean.Employeedto;
import com.employee.entity.Employee;
import com.employee.repository.IEmployeeRegistery;

@Service
public class EmpSrvcImpl implements IEmployeeService {

	@Autowired
	IEmployeeRegistery iEmployeeRegistery;
	
	Employee employee;
	Employeedto employeedto;
	List<Employee> employees;
	List<Employeedto> employeedtos;
	
	//new Employee
	@Transactional(readOnly = false)
	public Employee registerEmp(Employee employee) {
		return iEmployeeRegistery.save(employee);	
	}
	
	//get All employee
	@Transactional(readOnly = true)
	public List<Employeedto> getAllEmployee(){
		
		employeedtos = new ArrayList<Employeedto>();
		employees = iEmployeeRegistery.findAll();
		return listEmpTodto(employees);
	}
	
	//get employee
	@Transactional(readOnly = true)
	public Employeedto getEmployee(int employeeId) {	
	
		//employee = iEmployeeRegistery.findById(employeeId);
		employee = iEmployeeRegistery.findByid(employeeId);
		return empTodto(employee);
	}
	
	//update employee
	@Transactional(readOnly = false)
	public Employeedto updateEmployee(Employeedto employeedto) {
		
		 employee = iEmployeeRegistery.findByid(employeedto.getId());
		 employee = dtoToemp(employeedto, employee);
		 employee = iEmployeeRegistery.save(employee);
		 return empTodto(employee);
	}
	
	//soft delete employee
	@Transactional(readOnly = false)
	public void deleteEmployee(int employeeId) {
		iEmployeeRegistery.deleteById(employeeId);
	}
	
	//hard delete employee
	@Transactional(readOnly = false)
	public void hardDeleteEmployee(int employeeId) {
		iEmployeeRegistery.hardDeleteByid(employeeId);
	}
	
	//search by firstName
	@Transactional(readOnly = true)
	public List<Employeedto> searchByfirstName(String firstName){
		List<Employee> employees;
		employees = iEmployeeRegistery.findByfirstName(firstName);
		return listEmpTodto(employees);
	}
	
	//search by lastName
	@Transactional(readOnly = true)
	public List<Employeedto> searchBylastName(String lastName){
		List<Employee> employees;
		employees = iEmployeeRegistery.findBylastName(lastName);
		return listEmpTodto(employees);
	}
	
	//search by pincode
	@Transactional(readOnly = true)
	public List<Employeedto> searchByPincode(int pincode){
		List<Employee> employees;
		employees = iEmployeeRegistery.findBypincode(pincode);
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
	return employees.stream().map(emp -> {
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
