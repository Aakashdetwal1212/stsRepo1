package com.employee.registery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface IEmployeeRegistery extends JpaRepository<Employee, Integer> {

	public Employee findByemployeeId(int employeeId);
	public void deleteByemployeeId(int employeeId);	
}
