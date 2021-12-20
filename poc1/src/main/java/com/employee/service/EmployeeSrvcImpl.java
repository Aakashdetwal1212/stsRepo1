package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.bean.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NoNameResourceFoundException;
import com.employee.exception.NoPincodeResourceFoundException;
import com.employee.exception.NoSurnameResourceFoundException;
import com.employee.exception.ResourceAlreadyExistException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeSrvcImpl implements EmployeeService {

	@Autowired
	EmployeeRepository EmployeeRepository;

	Employee employee;
	EmployeeDto employeeDto;
	List<Employee> employees;
	List<EmployeeDto> employeeDtos;

	// add employee
	@Transactional(readOnly = false)
	public EmployeeDto addEmployee(Employee employee) {
		if (EmployeeRepository.existsById(employee.getId())) {
			throw new ResourceAlreadyExistException("User " + employee.getId() + " already Existed");
		}
		employee = EmployeeRepository.save(employee);
		return empTodto(employee);
	}

	// get all employee
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllEmployee() {
		employees = EmployeeRepository.findAll();
		return listEmpToDto(employees);
	}

	// get employee
	@Transactional(readOnly = true)
	public EmployeeDto getEmployeeById(int id) {
		if (EmployeeRepository.existsById(id))
			employee = EmployeeRepository.findByid(id);
		else
			throw new ResourceNotFoundException("given user " + id + " not available");
		return empTodto(employee);
	}

	// update employee
	@Transactional(readOnly = false)
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		if (EmployeeRepository.existsById(employeeDto.getId())) {
			employee = dtoToEmp(employeeDto, new Employee());
			employee = EmployeeRepository.save(employee);
		} else
			throw new ResourceNotFoundException("given user " + employeeDto.getId() + " not available");
		return empTodto(employee);
	}

	// soft delete employee
	@Transactional(readOnly = false)
	public boolean deleteEmployeeById(int id) throws ResourceNotFoundException {
		if (EmployeeRepository.existsById(id))
			EmployeeRepository.deleteById(id);
		else
			throw new ResourceNotFoundException("given user " + id + " not available");
		return true;
	}

	// hard delete employee
	@Transactional(readOnly = false)
	public boolean hardDeleteEmployeeById(int id) throws ResourceNotFoundException {
		if (EmployeeRepository.existsById(id))
			EmployeeRepository.hardDeleteByid(id);
		else
			throw new ResourceNotFoundException("given user " + id + " not available");
		return true;
	}

	// search by firstName
	@Transactional(readOnly = true)
	public List<EmployeeDto> searchByFirstName(String firstName) {
		if (EmployeeRepository.existsByFirstName(firstName))
			employees = EmployeeRepository.findByFirstName(firstName);
		else
			throw new NoNameResourceFoundException("given users by Name " + firstName + " is not available");
		return listEmpToDto(employees);
	}

	// search by lastName
	@Transactional(readOnly = true)
	public List<EmployeeDto> searchByLastName(String lastName) {
		if (EmployeeRepository.existsByLastName(lastName))
			employees = EmployeeRepository.findByLastName(lastName);
		else
			throw new NoSurnameResourceFoundException("given users by Sername " + lastName + " is not available");
		return listEmpToDto(employees);
	}

	// search by pincode
	@Transactional(readOnly = true)
	public List<EmployeeDto> searchByPincode(int pincode) {
		if (EmployeeRepository.existsByPincode(pincode))
			employees = EmployeeRepository.findByPincode(pincode);
		else
			throw new NoPincodeResourceFoundException("given users by Pincode " + pincode + " is not available");
		return listEmpToDto(employees);
	}

	// sort by dob/doj
	@Transactional(readOnly = true)
	public List<EmployeeDto> sortByField(String field) {
		employees = EmployeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
		return listEmpToDto(employees);
	}

	// converting employee to dto
	public EmployeeDto empTodto(Employee employee) {
		employeeDto = new EmployeeDto();
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setId(employee.getId());
		employeeDto.setPincode(employee.getPincode());
		employeeDto.setCity(employee.getCity());
		employeeDto.setBloodGroup(employee.getBloodGroup());
		employeeDto.setAge(employee.getAge());
		employeeDto.setDateOfBirth(employee.getDateOfBirth());
		employeeDto.setDateOfJoin(employee.getDateOfJoin());
		employeeDto.setDeleted(employee.isDeleted());
		return employeeDto;
	}

	// converting List employee to List dto
	public List<EmployeeDto> listEmpToDto(List<Employee> employees) {
		employeeDtos = new ArrayList<EmployeeDto>();
		return employeeDtos = employees.stream().map(emp -> {
			employeeDto = new EmployeeDto();
			employeeDto.setFirstName(emp.getFirstName());
			employeeDto.setLastName(emp.getLastName());
			employeeDto.setId(emp.getId());
			employeeDto.setPincode(emp.getPincode());
			employeeDto.setCity(emp.getCity());
			employeeDto.setBloodGroup(emp.getBloodGroup());
			employeeDto.setAge(emp.getAge());
			employeeDto.setDateOfBirth(emp.getDateOfBirth());
			employeeDto.setDateOfJoin(emp.getDateOfJoin());
			employeeDto.setDeleted(emp.isDeleted());
			return employeeDto;
		}).collect(Collectors.toList());
	}

	// converting employeeDto to employee
	public Employee dtoToEmp(EmployeeDto employeeDto, Employee employee) {
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setId(employeeDto.getId());
		employee.setPincode(employeeDto.getPincode());
		employee.setCity(employeeDto.getCity());
		employee.setBloodGroup(employeeDto.getBloodGroup());
		employee.setAge(employeeDto.getAge());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setDateOfJoin(employeeDto.getDateOfJoin());
		employee.setDeleted(employeeDto.isDeleted());
		return employee;
	}
}
