package com.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.bean.Employeedto;
import com.employee.entity.Employee;
import com.employee.repository.IEmployeeRepository;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc 
@SpringBootTest
class EmpSrvcImplTest {

	    @Mock
	    private IEmployeeRepository employeeRepository;

	    @InjectMocks
	    private EmpSrvcImpl empSrvcImpl;
	    private Employee employee;

	    public Employeedto buildResp() {
			Employeedto employeedto = new Employeedto();
			employeedto.setFirstName("bob");
			employeedto.setLastName("chohan");
			employeedto.setId(1010);
			employeedto.setPincode(919191);
			employeedto.setDoj(new Date());
			employeedto.setDob(new Date());
			employeedto.setCity("goa");
			employeedto.setBloodGroup("A+");
			employeedto.setAge(28);
			employeedto.setDeleted(false);
			return employeedto;
		}
	    
	    public Employee buildEmployee() {
	    	employee = new Employee();
			employee.setId(1010);
			employee.setFirstName("bob");
			employee.setLastName("chohan");
			employee.setPincode(919191);
			employee.setCity("goa");
			employee.setBloodGroup("A+");
			employee.setAge(28);
			employee.setDob(new Date());
			employee.setDoj(new Date());
			return employee;
	    }
	    
	    @Test
	    public void addEmpTest() {
	    	employee = new Employee();
			employee.setId(1010);
			employee.setFirstName("bob");
			employee.setLastName("chohan");
			employee.setPincode(919191);
			employee.setCity("goa");
			employee.setBloodGroup("A+");
			employee.setAge(28);
			employee.setDob(new Date());
			employee.setDoj(new Date());
	
	    	Mockito.when(employeeRepository.existsByid(buildEmployee().getId())).thenReturn(false);
	        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
	        Employeedto employeedto = empSrvcImpl.registerEmp(employee);
	        assertThat(employeedto.getFirstName()).isEqualTo(buildResp().getFirstName());
	    }

	    @Test
	    public void getEmpTest() {
	       
            Mockito.when(employeeRepository.findByid(1010)).thenReturn(buildEmployee());
            Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);

            Employeedto employeedto = empSrvcImpl.getEmployee(1010);
	        assertThat(employeedto.getFirstName()).isEqualTo(buildResp().getFirstName());
	    }

	    @Test
	    public void updateEmployeeTest() {
 	
	    	Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);
            Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(buildEmployee());
	        
	        Employeedto employeedto = empSrvcImpl.updateEmployee(buildResp());
	        assertThat(employeedto.getFirstName()).isEqualTo(buildResp().getFirstName());
	    }


	    @Test
	    public void deleteEmployeeTest() {
	
            Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);
	        Mockito.when(employeeRepository.deleteById(1010)).thenReturn(1);
	        int value = empSrvcImpl.deleteEmployee(1010);
	        assertThat(value).isEqualTo(1);
	    }

	    @Test
	    public void hardDeleteEmployeeTest() {
	   
	        Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);
	        Mockito.when(employeeRepository.hardDeleteByid(1010)).thenReturn(true);
	        boolean flag = empSrvcImpl.hardDeleteEmployee(1010);
	        assertThat(flag).isTrue();
	    }

}
