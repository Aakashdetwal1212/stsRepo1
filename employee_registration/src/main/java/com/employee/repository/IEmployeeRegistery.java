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
public interface IEmployeeRegistery extends JpaSpecificationExecutor<Employee>,JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {

	public Employee findByid(int employeeId);

	@Modifying
	@Query("UPDATE Employee SET deleted=1 WHERE id= :employeeId")
	public void deleteById(int employeeId);	
	

	@Modifying
	@Query("DELETE FROM Employee e WHERE e.id = :employeeId")
	public void hardDeleteByid(@Param("employeeId") int employeeId);
    
	public List<Employee> findByfirstName(String firstName);
	public List<Employee> findBylastName(String lastName);
	public List<Employee> findBypincode(int pincode);
	public boolean existsByid(int id);
	
	public boolean existsByfirstName(String firstName);
	public boolean existsBylastName(String lastName);
	public boolean existsBypincode(int pincode);
}
