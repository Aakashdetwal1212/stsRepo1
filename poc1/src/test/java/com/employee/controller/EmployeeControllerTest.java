package com.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.junit.runner.Result;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.bean.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NoNameResourceFoundException;
import com.employee.exception.NoPincodeResourceFoundException;
import com.employee.exception.NoSurnameResourceFoundException;
import com.employee.exception.ResourceAlreadyExistException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeSrvcImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeSrvcImpl empSrvcImpl;
	@MockBean
	EmployeeRepository repository;

	@Mock
	EmployeeController controller;

	MvcResult mvcResult;

	Employee employee;

	private static ObjectMapper mapper = new ObjectMapper();

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

	@Test
	public void addEmployeeTest() throws Exception {
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

		Mockito.when(empSrvcImpl.addEmployee(ArgumentMatchers.any())).thenReturn(buildResp());
		String json = mapper.writeValueAsString(employee);

		mockMvc.perform(post("/emp/addemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is("bob"))).andExpect(jsonPath("$.lastName", is("chohan")))
				.andExpect(jsonPath("$.pincode", is(919191))).andExpect(jsonPath("$.id", is(1010)))
				.andExpect(jsonPath("$.city", is("goa"))).andExpect(jsonPath("$.bloodGroup", is("A+")))
				.andExpect(jsonPath("$.age", is(28))).andExpect(jsonPath("$.deleted", is(false)));
	}

	@Test
	public void getEmployeeByIdTest() throws Exception {
		Mockito.when(empSrvcImpl.getEmployeeById(1010)).thenReturn(buildResp());

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/getemp/1010")
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();

		mockMvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("bob")))
				.andExpect(jsonPath("$.lastName", is("chohan"))).andExpect(jsonPath("$.pincode", is(919191)))
				.andExpect(jsonPath("$.id", is(1010))).andExpect(jsonPath("$.city", is("goa")))
				.andExpect(jsonPath("$.bloodGroup", is("A+"))).andExpect(jsonPath("$.age", is(28)))
				.andExpect(jsonPath("$.deleted", is(false)));
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setFirstName("bob");
		employeeDto.setLastName("chohan");
		employeeDto.setId(20302);
		employeeDto.setPincode(919191);
		employeeDto.setDateOfBirth(new Date());
		employeeDto.setDateOfJoin(new Date());
		employeeDto.setCity("goa");
		employeeDto.setBloodGroup("A+");
		employeeDto.setAge(28);
		employeeDto.setDeleted(false);

		Mockito.when(empSrvcImpl.updateEmployee(ArgumentMatchers.any())).thenReturn(employeeDto);
		String json = mapper.writeValueAsString(employeeDto);
		mockMvc.perform(put("/emp/updateemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("bob"))).andExpect(jsonPath("$.lastName", is("chohan")))
				.andExpect(jsonPath("$.pincode", is(919191))).andExpect(jsonPath("$.id", is(20302)))
				.andExpect(jsonPath("$.city", is("goa"))).andExpect(jsonPath("$.bloodGroup", is("A+")))
				.andExpect(jsonPath("$.age", is(28))).andExpect(jsonPath("$.deleted", is(false)));
	}

	@Test
	public void deleteEmployeeByIdTest() throws Exception {
		String uri = "/emp/deleteemp/101010";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "employee number" + 101010 + " Deactivated");
	}

	@Test
	public void hardDeleteEmployeeByIdTest() throws Exception {
		String uri = "/emp/purgeemp/101010";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "employee number " + 101010 + " deleted");
	}

	@Test
	public void getAllEmployeeTest() throws Exception {

		EmployeeDto employee1 = new EmployeeDto("rolley", "methue", 1010, 27, "ratlam", 457001, "A+", new Date(),
				new Date(), false);
		EmployeeDto employee2 = new EmployeeDto("Alex", "Gussin", 2020, 25, "channai", 223344, "A+", new Date(),
				new Date(), false);

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		employees.add(employee1);
		employees.add(employee2);

		Mockito.when(empSrvcImpl.getAllEmployee()).thenReturn(employees);
		mockMvc.perform(MockMvcRequestBuilders.get("/emp/getallemp").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is("rolley")));
	}

	@Test
	public void searchEmployeesByfirstNameTest() throws Exception {

		EmployeeDto employee1 = new EmployeeDto("alex", "Gussin", 1010, 27, "ratlam", 457001, "A+", new Date(),
				new Date(), false);
		EmployeeDto employee2 = new EmployeeDto("alex", "nelson", 2020, 25, "ratlam", 457001, "A+", new Date(),
				new Date(), false);

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		employees.add(employee1);
		employees.add(employee2);
		String firstName = "alex";

		Mockito.when(empSrvcImpl.searchByFirstName(firstName)).thenReturn(employees);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/name/firstName")
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void searchEmployeesByLastNameTest() throws Exception {

		EmployeeDto employee1 = new EmployeeDto("alex", "Gussin", 1010, 27, "ratlam", 457001, "A+", new Date(),
				new Date(), false);
		EmployeeDto employee2 = new EmployeeDto("jiyan", "Gussin", 2020, 25, "ratlam", 457001, "A+", new Date(),
				new Date(), false);

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		employees.add(employee1);
		employees.add(employee2);
		String lastName = "Gussin";

		Mockito.when(empSrvcImpl.searchByLastName(lastName)).thenReturn(employees);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/surname/lastName")
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void searchEmployeesByPincodeTest() throws Exception {

		EmployeeDto employee1 = new EmployeeDto("alex", "Gussin", 1010, 27, "ratlam", 457001, "A+", new Date(),
				new Date(), false);
		EmployeeDto employee2 = new EmployeeDto("jiyan", "Gussin", 2020, 25, "ratlam", 457001, "A+", new Date(),
				new Date(), false);

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		employees.add(employee1);
		employees.add(employee2);
		int pincode = 457001;

		Mockito.when(empSrvcImpl.searchByPincode(pincode)).thenReturn(employees);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/pincode/" + pincode)
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void sortByFieldTest() throws Exception {

		EmployeeDto employee1 = new EmployeeDto("alex", "Gussin", 1010, 27, "ratlam", 457001, "A+", new Date(),
				new Date(), false);
		EmployeeDto employee2 = new EmployeeDto("jiyan", "Gussin", 2020, 25, "ratlam", 457001, "A+", new Date(),
				new Date(), false);

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		employees.add(employee1);
		employees.add(employee2);
		String field = "firstName";

		Mockito.when(empSrvcImpl.sortByField(field)).thenReturn(employees);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/sort/field")
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void addEmployeeExceptionTest() throws Exception {
		employee = new Employee("bob", "thomas", 1010, 22, "indore", 44450, "A+", new Date(), new Date());

		Mockito.when(empSrvcImpl.addEmployee(ArgumentMatchers.any())).thenThrow(ResourceAlreadyExistException.class);
		String json = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/emp/addemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isAlreadyReported());
	}

	@Test
	public void updateEmployeeExceptionTest() throws Exception {
		EmployeeDto employeedto = new EmployeeDto("bob", "thomas", 1010, 22, "ratlam", 457001, "A+", new Date(),
				new Date(), false);

		Mockito.when(empSrvcImpl.updateEmployee(ArgumentMatchers.any())).thenThrow(ResourceNotFoundException.class);
		String json = mapper.writeValueAsString(employeedto);
		mockMvc.perform(put("/emp/updateemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	public void getEmployeeByIdExceptionTest() throws Exception {
		int id = 1010;
		Mockito.when(empSrvcImpl.getEmployeeById(id)).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(get("/emp/getemp/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	public void getEmployeeByIdUnKnownExceptionTest() throws Exception {
		int id = 1010;
		Mockito.when(empSrvcImpl.getEmployeeById(id)).thenThrow(ClassCastException.class);
		mockMvc.perform(get("/emp/getemp/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void searchEmloyeeByfirstNameExceptionTest() throws Exception {
		String firstName = "alex";
		Mockito.when(empSrvcImpl.searchByFirstName(firstName)).thenThrow(NoNameResourceFoundException.class);
		mockMvc.perform(get("/emp/name/" + firstName).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	public void searchEmployeeBylastNameExceptionTest() throws Exception {
		String lastName = "martin";
		Mockito.when(empSrvcImpl.searchByLastName(lastName)).thenThrow(NoSurnameResourceFoundException.class);
		mockMvc.perform(get("/emp/surname/" + lastName).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	public void searchEmployeeByPincodeExceptionTest() throws Exception {
		int pincode = 457001;
		Mockito.when(empSrvcImpl.searchByPincode(pincode)).thenThrow(NoPincodeResourceFoundException.class);
		mockMvc.perform(get("/emp/pincode/" + pincode).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	public void addEmployeeValidationExceptonTest() throws Exception {
		employee = new Employee("bob", "", 1010, 26, "ratlam", 457001, "A+", new Date(), new Date());

		Mockito.when(empSrvcImpl.addEmployee(ArgumentMatchers.any())).thenReturn(buildResp());
		String json = mapper.writeValueAsString(employee);

		mockMvc.perform(post("/emp/addemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
	}
}
