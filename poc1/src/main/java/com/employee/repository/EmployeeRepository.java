package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>,JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {

	public Employee findByid(int employeeId);

	@Modifying
	@Query("UPDATE Employee SET deleted=1 WHERE id= :employeeId")
	public int deleteById(int employeeId);	
	

	@Modifying
	@Query("DELETE FROM Employee e WHERE e.id = :employeeId")
	public boolean hardDeleteByid(@Param("employeeId") int employeeId);
    
	public List<Employee> findByFirstName(String firstName);
	public List<Employee> findByLastName(String lastName);
	public List<Employee> findByPincode(int pincode);
	public boolean existsById(int id);
	
	public boolean existsByFirstName(String firstName);
	public boolean existsByLastName(String lastName);
	public boolean existsByPincode(int pincode);
}
