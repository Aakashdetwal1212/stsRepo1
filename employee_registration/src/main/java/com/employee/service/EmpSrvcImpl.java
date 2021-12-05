package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.registery.IEmployeeRegistery;

@Service
public class EmpSrvcImpl implements IEmployeeService {

	@Autowired
	IEmployeeRegistery iEmployeeRegistery;
	
	public Employee registerEmp(Employee employee) {
		return iEmployeeRegistery.save(employee);	
	}
}
