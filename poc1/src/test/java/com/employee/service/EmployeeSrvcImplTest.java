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
class EmployeeSrvcImplTest {

	@Mock
	private IEmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeSrvcImpl empSrvcImpl;
	private Employee employee;

	public Employeedto buildResp() {
		Employeedto employeedto = new Employeedto();
		employeedto.setFirstName("bob");
		employeedto.setLastName("chohan");
		employeedto.setId(1010);
		employeedto.setPincode(919191);
		employeedto.setDateOfJoin(new Date());
		employeedto.setDateOfBirth(new Date());
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
		employee.setDateOfBirth(new Date());
		employee.setDateOfJoin(new Date());
		return employee;
	}

	@Test
	public void addEmployeeTest() {
		employee = new Employee();
		employee.setId(1010);
		employee.setFirstName("bob");
		employee.setLastName("chohan");
		employee.setPincode(919191);
		employee.setCity("goa");
		employee.setBloodGroup("A+");
		employee.setAge(28);
		employee.setDateOfBirth(new Date());
		employee.setDateOfJoin(new Date());

		Mockito.when(employeeRepository.existsByid(buildEmployee().getId())).thenReturn(false);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		Employeedto employeedto = empSrvcImpl.addEmployee(employee);
		assertThat(employeedto.getFirstName()).isEqualTo(buildResp().getFirstName());
	}

	@Test
	public void getEmployeeByIdTest() {
		Mockito.when(employeeRepository.findByid(1010)).thenReturn(buildEmployee());
		Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);

		Employeedto employeedto = empSrvcImpl.getEmployeeById(1010);
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
	public void deleteEmployeeByIdTest() {
		Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);
		Mockito.when(employeeRepository.deleteById(1010)).thenReturn(1);
		boolean flag = empSrvcImpl.deleteEmployeeById(1010);
		assertThat(flag).isTrue();
	}

	@Test
	public void hardDeleteEmployeeByIdTest() {
		Mockito.when(employeeRepository.existsByid(1010)).thenReturn(true);
		Mockito.when(employeeRepository.hardDeleteByid(1010)).thenReturn(true);
		boolean flag = empSrvcImpl.hardDeleteEmployeeById(1010);
		assertThat(flag).isTrue();
	}

}
