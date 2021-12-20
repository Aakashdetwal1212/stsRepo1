package com.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.employee.bean.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NoNameResourceFoundException;
import com.employee.exception.NoPincodeResourceFoundException;
import com.employee.exception.NoSurnameResourceFoundException;
import com.employee.exception.ResourceAlreadyExistException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class EmployeeSrvcImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeSrvcImpl empSrvcImpl;
	private Employee employee;

	public EmployeeDto buildResp() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setFirstName("bob");
		employeeDto.setLastName("chohan");
		employeeDto.setId(1010);
		employeeDto.setPincode(919191);
		employeeDto.setDateOfJoin(new Date());
		employeeDto.setDateOfBirth(new Date());
		employeeDto.setCity("goa");
		employeeDto.setBloodGroup("A+");
		employeeDto.setAge(28);
		employeeDto.setDeleted(false);
		return employeeDto;
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

		Mockito.when(employeeRepository.existsById(buildEmployee().getId())).thenReturn(false);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		EmployeeDto employeeDto = empSrvcImpl.addEmployee(employee);
		assertThat(employeeDto.getFirstName()).isEqualTo(buildResp().getFirstName());
	}

	@Test
	public void getEmployeeByIdTest() {
		Mockito.when(employeeRepository.findByid(1010)).thenReturn(buildEmployee());
		Mockito.when(employeeRepository.existsById(1010)).thenReturn(true);

		EmployeeDto employeeDto = empSrvcImpl.getEmployeeById(1010);
		assertThat(employeeDto.getFirstName()).isEqualTo(buildResp().getFirstName());
	}

	@Test
	public void getAllEmployeesTest() {
		Employee employee1 = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
		Employee employee2 = new Employee("Alex", "Gussin", 2020, 25, "channai", 223344, "A+", new Date(), new Date());
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(employee1);
		listEmp.add(employee2);

		Mockito.when(employeeRepository.findAll()).thenReturn(listEmp);

		List<EmployeeDto> listEmpDto = empSrvcImpl.getAllEmployee();

		assertEquals(2, listEmpDto.size());
	}

	@Test
	public void updateEmployeeTest() {
		Mockito.when(employeeRepository.existsById(1010)).thenReturn(true);
		Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(buildEmployee());

		EmployeeDto employeeDto = empSrvcImpl.updateEmployee(buildResp());
		assertThat(employeeDto.getFirstName()).isEqualTo(buildResp().getFirstName());
	}

	@Test
	public void deleteEmployeeByIdTest() {
		Mockito.when(employeeRepository.existsById(1010)).thenReturn(true);
		Mockito.when(employeeRepository.deleteById(1010)).thenReturn(1);
		boolean flag = empSrvcImpl.deleteEmployeeById(1010);
		assertThat(flag).isTrue();
	}

	@Test
	public void hardDeleteEmployeeByIdTest() {
		Mockito.when(employeeRepository.existsById(1010)).thenReturn(true);
		Mockito.when(employeeRepository.hardDeleteByid(1010)).thenReturn(true);
		boolean flag = empSrvcImpl.hardDeleteEmployeeById(1010);
		assertThat(flag).isTrue();
	}
	
	@Test
	public void searchEmployeeByFirstNameTest() {
		Employee employee1 = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
		Employee employee2 = new Employee("alex", "Gussin", 2020, 25, "channai", 223344, "A+", new Date(), new Date());
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(employee1);
		listEmp.add(employee2);
		
		Mockito.when(employeeRepository.existsByFirstName("alex")).thenReturn(true);
		Mockito.when(employeeRepository.findByFirstName("alex")).thenReturn(listEmp);
		List<EmployeeDto> listEmpDto = empSrvcImpl.searchByFirstName("alex");
		
		assertEquals(2, listEmpDto.size());
	}
	
	@Test
	public void searchEmployeeByLastNameTest() {
		Employee employee1 = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
		Employee employee2 = new Employee("rock", "martin", 2020, 25, "channai", 223344, "A+", new Date(), new Date());
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(employee1);
		listEmp.add(employee2);
		
		Mockito.when(employeeRepository.existsByLastName("martin")).thenReturn(true);
		Mockito.when(employeeRepository.findByLastName("martin")).thenReturn(listEmp);
		List<EmployeeDto> listEmpDto = empSrvcImpl.searchByLastName("martin");
		
		assertEquals(2, listEmpDto.size());
	}
	
	@Test
	public void searchEmployeeByPincodeTest() {
		Employee employee1 = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
		Employee employee2 = new Employee("rock", "martin", 2020, 25, "ratlam", 457001, "A+", new Date(), new Date());
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(employee1);
		listEmp.add(employee2);
		
		Mockito.when(employeeRepository.existsByPincode(457001)).thenReturn(true);
		Mockito.when(employeeRepository.findByPincode(457001)).thenReturn(listEmp);
		List<EmployeeDto> listEmpDto = empSrvcImpl.searchByPincode(457001);
		
		assertEquals(2, listEmpDto.size());
	}
	

	@Test
	public void sortByFieldTest() {
		Employee employee1 = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
		Employee employee2 = new Employee("rock", "martin", 2020, 25, "ratlam", 457001, "A+", new Date(), new Date());
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(employee1);
		listEmp.add(employee2);
		String field = "firstName";
		Mockito.when(employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field))).thenReturn(listEmp);
		List<EmployeeDto> listEmpDto = empSrvcImpl.sortByField(field);
		
		assertEquals(2, listEmpDto.size());
	}

		@Test
		public void addEmployeeExceptionTest() {
			Employee employee = new Employee("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date());
			
			Mockito.when(employeeRepository.existsById(1010)).thenReturn(true);
			ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class, () -> { empSrvcImpl.addEmployee(employee); });
		    assertTrue(exception.getMessage().contains("User " + employee.getId() + " already Existed"));
		}

		@Test
		public void getEmployeeByIdExceptionTest() {
			Mockito.when(employeeRepository.existsById(1010)).thenReturn(false);
			ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> { empSrvcImpl.getEmployeeById(1010); });
		    assertTrue(exception.getMessage().contains("given user " + 1010 + " not available"));
		}

		@Test
		public void updateEmployeeExceptionTest() {
			EmployeeDto employeeDto = new EmployeeDto("alex", "martin", 1010, 22, "ratlam", 457001, "A+", new Date(), new Date(),false);
			
			Mockito.when(employeeRepository.existsById(1010)).thenReturn(false);
			ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> { empSrvcImpl.updateEmployee(employeeDto); });
		    assertTrue(exception.getMessage().contains("given user " + employeeDto.getId() + " not available"));
		}
	
		@Test
		public void deleteEmployeeByIdExceptionTest() {
			Mockito.when(employeeRepository.existsById(1010)).thenReturn(false);
			ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> { empSrvcImpl.deleteEmployeeById(1010); });
		    assertTrue(exception.getMessage().contains("given user " + 1010 + " not available"));
		}

		@Test
		public void hardDeleteEmployeeByIdExceptionTest() {
			Mockito.when(employeeRepository.existsById(1010)).thenReturn(false);
			ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> { empSrvcImpl.hardDeleteEmployeeById(1010); });
		    assertTrue(exception.getMessage().contains("given user " + 1010 + " not available"));
		}
	
		@Test
		public void searchByfirstNameExceptionTest() {
			String firstName = "methue";
			Mockito.when(employeeRepository.existsByFirstName(firstName)).thenReturn(false);
			NoNameResourceFoundException exception = assertThrows(NoNameResourceFoundException.class, () -> { empSrvcImpl.searchByFirstName(firstName); });
		    assertTrue(exception.getMessage().contains(("given users by Name " + firstName + " is not available")));
		}
	
		@Test
		public void searchBylastNameExceptionTest() {
			String lastName = "martin";
			Mockito.when(employeeRepository.existsByLastName(lastName)).thenReturn(false);
			NoSurnameResourceFoundException exception = assertThrows(NoSurnameResourceFoundException.class, () -> { empSrvcImpl.searchByLastName(lastName); });
		    assertTrue(exception.getMessage().contains(("given users by Sername " + lastName + " is not available")));
		}
		
		@Test
		public void searchByPincodeExceptionTest() {
			int pincode = 457001;
			Mockito.when(employeeRepository.existsByPincode(pincode)).thenReturn(false);
			NoPincodeResourceFoundException exception = assertThrows(NoPincodeResourceFoundException.class, () -> { empSrvcImpl.searchByPincode(pincode); });
		    assertTrue(exception.getMessage().contains(("given users by Pincode " + pincode + " is not available")));
		}
}

