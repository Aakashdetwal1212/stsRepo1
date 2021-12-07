package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface IEmployeeRegistery extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {

	public Employee findByid(int employeeId);

	@Modifying
	@Query("UPDATE Employee SET deleted=1 WHERE id= :employeeId")
	public void deleteById(int employeeId);	
	

	@Modifying
	@Query("DELETE FROM Employee e WHERE e.id = :employeeId")
	public void hardDeleteByid(@Param("employeeId") int employeeId);
    
}
