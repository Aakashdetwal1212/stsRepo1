package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.employee.entity.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class IEmployeeRepositoryTest {

	@Autowired
	IEmployeeRepository repository;

	@Test
	public void addEmployeeTest() {
		Employee employee = repository
				.save(new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date()));
		assertThat(employee).hasFieldOrPropertyWithValue("firstName", "piter");
		assertThat(employee).hasFieldOrPropertyWithValue("pincode", 333999);
		assertThat(employee).hasFieldOrPropertyWithValue("city", "ratlam");
	}

	@Test
	public void findAllEmployeeTest() {
		Employee employee1 = new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date());
		Employee employee2 = new Employee("jack", "john", 1020, 27, "ratlam", 333000, "A+", new Date(), new Date());

		repository.save(employee1);
		repository.save(employee2);
		assertNotNull(repository.findAll());
	}

	@Test
	public void findEmployeeByIdTest() {
		Employee employee1 = new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date());
		Employee employee2 = new Employee("jack", "john", 1020, 27, "ratlam", 333000, "A+", new Date(), new Date());
		repository.save(employee1);
		repository.save(employee2);

		Employee employee3 = repository.findByid(employee1.getId());
		assertThat(employee3).isEqualTo(employee1);
	}

	@Test
	public void findEmployeeByfirstNameTest() {

		Employee employee1 = repository
				.save(new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date()));
		Employee employee2 = repository
				.save(new Employee("jack", "john", 1020, 27, "ratlam", 333000, "A+", new Date(), new Date()));

		List<Employee> employees = repository.findByfirstName(employee1.getFirstName());
		assertThat(employees).hasSize(1).contains(employee1);
	}

	@Test
	public void findEmployeeBylastNameTest() {
		Employee employee1 = repository
				.save(new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date()));
		Employee employee2 = repository
				.save(new Employee("jack", "john", 1020, 27, "ratlam", 333000, "A+", new Date(), new Date()));

		List<Employee> employees = repository.findBylastName(employee1.getLastName());
		assertThat(employees).hasSize(1).contains(employee1);
	}

	@Test
	public void findEmployeeByPincodeTest() {
		Employee employee1 = new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date());
		Employee employee2 = new Employee("jack", "john", 1020, 27, "indore", 333000, "A+", new Date(), new Date());
		Employee employee3 = new Employee("marry", "walter", 1030, 27, "ratlam", 333999, "A+", new Date(), new Date());
		repository.save(employee1);
		repository.save(employee2);
		repository.save(employee3);

		List<Employee> employees = repository.findBypincode(employee1.getPincode());
		assertThat(employees).hasSize(2).contains(employee1, employee3);
	}

	@Test
	public void updateById() {
		Employee employee1 = new Employee("piter", "parker", 2323, 27, "ratlam", 333999, "A+", new Date(), new Date());
		Employee employee2 = new Employee("jack", "john", 3434, 27, "indore", 333000, "A+", new Date(), new Date());
		Employee employee3 = new Employee("marry", "walter", 4545, 27, "ratlam", 333999, "A+", new Date(), new Date());
		repository.save(employee1);
		repository.save(employee2);
		repository.save(employee3);

		Employee employee = new Employee("marry", "walter", 4545, 29, "pune", 333222, "A+", new Date(), new Date());

		Employee employeeNew = repository.findByid(4545);
		employeeNew.setFirstName(employee.getFirstName());
		employeeNew.setLastName(employee.getLastName());
		employeeNew.setId(employee.getId());
		employeeNew.setPincode(employee.getPincode());
		employeeNew.setDateOfBirth(employee.getDateOfBirth());
		employeeNew.setDateOfJoin(employee.getDateOfJoin());
		employeeNew.setAge(employee.getAge());
		employeeNew.setCity(employee.getCity());
		employeeNew.setBloodGroup(employee.getBloodGroup());

		repository.save(employeeNew);
		Employee checkEmp = repository.findByid(4545);

		assertThat(checkEmp.getId()).isEqualTo(employee3.getId());
		assertThat(checkEmp.getFirstName()).isEqualTo(employee3.getFirstName());
		assertThat(checkEmp.getCity()).isEqualTo(employee3.getCity());
		assertThat(checkEmp.getPincode()).isEqualTo(employee3.getPincode());
		assertThat(checkEmp.getAge()).isEqualTo(employee3.getAge());
	}

	@Test
	public void deleteByIdTest() {
		Employee employee1 = new Employee("piter", "parker", 1010, 27, "ratlam", 333999, "A+", new Date(), new Date());
		Employee employee2 = new Employee("jack", "john", 1020, 27, "indore", 333000, "A+", new Date(), new Date());
		Employee employee3 = new Employee("marry", "walter", 10300, 27, "ratlam", 333999, "A+", new Date(), new Date());
		repository.save(employee1);
		repository.save(employee2);
		repository.save(employee3);

		repository.deleteById(1010);
	}
}
