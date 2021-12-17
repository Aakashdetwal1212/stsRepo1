package com.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.employee.bean.Employeedto;
import com.employee.entity.Employee;
import com.employee.service.EmpSrvcImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = EmpController.class)
public class EmpControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmpSrvcImpl empSrvcImpl;
	
	@Mock
	EmpController controller;
	
	Employee employee;
	
    private static ObjectMapper mapper = new ObjectMapper();

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
	
	@Test
	public void postEmployeeTest() throws Exception {
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
			
		
			Mockito.when(empSrvcImpl.registerEmp(ArgumentMatchers.any())).thenReturn(buildResp());
		    String json = mapper.writeValueAsString(employee);
		    mockMvc.perform(post("/emp/new").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
	                .content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
        		    .andExpect(jsonPath("$.firstName",is("bob")))
		            .andExpect(jsonPath("$.lastName", is("chohan")))
		            .andExpect(jsonPath("$.pincode", is(919191)))
		            .andExpect(jsonPath("$.id", is(1010)))
		            .andExpect(jsonPath("$.city", is("goa")))
		            .andExpect(jsonPath("$.bloodGroup", is("A+")))
		            .andExpect(jsonPath("$.age", is(28)))
		            .andExpect(jsonPath("$.deleted", is(false)));
//		            .andExpect(jsonPath("$.doj",  is(new Date())))
//		            .andExpect(jsonPath("$.dob", is(new Date())));		
	    }   
	
	 @Test  
     public void getEmployeeTest() throws Exception {
        	int id = 1010;
        	Mockito.when(empSrvcImpl.getEmployee(id)).thenReturn(buildResp());
        	
        	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/getemp/1010")
 	        		.contentType(MediaType.APPLICATION_JSON_VALUE);
        	 
        	MvcResult mvcResult = mockMvc.perform(builder).andReturn(); 
        	
        	mockMvc.perform(builder).andExpect(status().isFound())
        	                        .andExpect(jsonPath("$.firstName",is("bob")))
        	                        .andExpect(jsonPath("$.lastName", is("chohan")))
        	                        .andExpect(jsonPath("$.pincode", is(919191)))
        	                        .andExpect(jsonPath("$.id", is(1010)))
        	                        .andExpect(jsonPath("$.city", is("goa")))
        	                        .andExpect(jsonPath("$.bloodGroup", is("A+")))
        	                        .andExpect(jsonPath("$.age", is(28)))
        	                        .andExpect(jsonPath("$.deleted", is(false)));
        	             //           .andExpect(jsonPath("$.doj", is(LocalDate.of(1997, 1, 1))))
        	             //           .andExpect(jsonPath("$.dob",is(LocalDate.of(1997, 1, 1))));
        }
	 
	    @Test
	    public void putEmployyeTest() throws Exception {
		  Employeedto employeedto = new Employeedto();
			employeedto.setFirstName("bob");
			employeedto.setLastName("chohan");
			employeedto.setId(20302);
			employeedto.setPincode(919191);
			employeedto.setDoj(new Date());
			employeedto.setDob(new Date());
			employeedto.setCity("goa");
			employeedto.setBloodGroup("A+");
			employeedto.setAge(28);
			employeedto.setDeleted(false);
	   
	        Mockito.when(empSrvcImpl.updateEmployee(ArgumentMatchers.any())).thenReturn(employeedto);
	        String json = mapper.writeValueAsString(employeedto);
	        mockMvc.perform(put("/emp/updateemp").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
	                .content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstName",is("bob")))
                    .andExpect(jsonPath("$.lastName", is("chohan")))
                    .andExpect(jsonPath("$.pincode", is(919191)))
                    .andExpect(jsonPath("$.id", is(20302)))
                    .andExpect(jsonPath("$.city", is("goa")))
                    .andExpect(jsonPath("$.bloodGroup", is("A+")))
                    .andExpect(jsonPath("$.age", is(28)))
                    .andExpect(jsonPath("$.deleted", is(false)));
 //                 .andExpect(jsonPath("$.doj", is(LocalDate.of(1997, 1, 1))))
 //                 .andExpect(jsonPath("$.dob",is(LocalDate.of(1997, 1, 1))));
	    }
	  
	    @Test
	    public void deleteEmployeeTest() throws Exception {
	       String uri = "/emp/deleteemp/101010";
	       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	       int status = mvcResult.getResponse().getStatus();
	       assertEquals(200, status);
	       String content = mvcResult.getResponse().getContentAsString();
	       assertEquals(content, "emp number"+101010+" Deactivated");
	    }
	    
	    @Test
	    public void hardDeleteEmployeeTest() throws Exception {
	       String uri = "/emp/hdelete/101010";
	       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	       int status = mvcResult.getResponse().getStatus();
	       assertEquals(200, status);
	       String content = mvcResult.getResponse().getContentAsString();
	       assertEquals(content, "emp number "+ 101010 +" deleted");
	    }
	    
	    @Test
	    public void getEmployeesTest() throws Exception {
	         
	            Employeedto employee1 = new Employeedto("rolley", "methue",1010, 27,"ratlam",457001,"A+",new Date(),new Date(),false);
	            Employeedto employee2 = new Employeedto("Alex", "Gussin", 2020,25,"channai",223344,"A+", new Date(),new Date(),false);
	           
	            List<Employeedto> employees = new ArrayList<Employeedto>();	          
	            employees.add(employee1);
	            employees.add(employee2);
	            
	            Mockito.when(empSrvcImpl.getAllEmployee()).thenReturn(employees);
	            
	            mockMvc.perform(MockMvcRequestBuilders.get("/emp/getall") 
	            .contentType(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].firstName", is("rolley")));
	    }
	    
	    @Test
	    public void getEmployeesByfirstName() throws Exception {
	    	
	    	   Employeedto employee1 = new Employeedto("alex", "Gussin",1010, 27,"ratlam",457001,"A+",new Date(),new Date(),false);
	           Employeedto employee2 = new Employeedto("alex", "nelson", 2020,25,"ratlam",457001,"A+", new Date(),new Date(),false);
	           
	           List<Employeedto> employees = new ArrayList<Employeedto>();	          
	           employees.add(employee1);
	           employees.add(employee2);
	           String firstName = "alex";
	           
        	   Mockito.when(empSrvcImpl.searchByfirstName(firstName)).thenReturn(employees);
        	   
               MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/searchbyname/firstName")
 	        		.contentType(MediaType.APPLICATION_JSON_VALUE);
        	 
               MvcResult mvcResult = mockMvc.perform(builder).andReturn(); 
               mockMvc.perform(builder).andExpect(status().isFound());                 
	    }
	    
	    
	    @Test
	    public void getEmployeesByLastName() throws Exception {
	    	
	    	   Employeedto employee1 = new Employeedto("alex", "Gussin",1010, 27,"ratlam",457001,"A+",new Date(),new Date(),false);
	           Employeedto employee2 = new Employeedto("jiyan", "Gussin", 2020,25,"ratlam",457001,"A+", new Date(),new Date(),false);
	           
	           List<Employeedto> employees = new ArrayList<Employeedto>();	          
	           employees.add(employee1);
	           employees.add(employee2);
	           String lastName = "Gussin";
	           
        	   Mockito.when(empSrvcImpl.searchBylastName(lastName)).thenReturn(employees);
        	   
               MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/searchbysername/lastName")
 	        		.contentType(MediaType.APPLICATION_JSON_VALUE);
        	 
               MvcResult mvcResult = mockMvc.perform(builder).andReturn(); 
               mockMvc.perform(builder).andExpect(status().isFound());                 
	    }
	    
	    @Test
	    public void getEmployeesByPincode() throws Exception {
	    	
	    	   Employeedto employee1 = new Employeedto("alex", "Gussin",1010, 27,"ratlam",457001,"A+",new Date(),new Date(),false);
	           Employeedto employee2 = new Employeedto("jiyan", "Gussin", 2020,25,"ratlam",457001,"A+", new Date(),new Date(),false);
	           
	           List<Employeedto> employees = new ArrayList<Employeedto>();	          
	           employees.add(employee1);
	           employees.add(employee2);
	           int pincode = 457001;
	           
        	   Mockito.when(empSrvcImpl.searchByPincode(pincode)).thenReturn(employees);
        	   
               MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/searchbypincode/"+pincode)
 	        		.contentType(MediaType.APPLICATION_JSON_VALUE);
        	 
               MvcResult mvcResult = mockMvc.perform(builder).andReturn(); 
               mockMvc.perform(builder).andExpect(status().isFound());                 
	    }
	    
	    @Test
	    public void sortByFieldTest() throws Exception {
	    	
	    	   Employeedto employee1 = new Employeedto("alex", "Gussin",1010, 27,"ratlam",457001,"A+",new Date(),new Date(),false);
	           Employeedto employee2 = new Employeedto("jiyan", "Gussin", 2020,25,"ratlam",457001,"A+", new Date(),new Date(),false);
	           
	           List<Employeedto> employees = new ArrayList<Employeedto>();	          
	           employees.add(employee1);
	           employees.add(employee2);
	           String field = "firstName";
	           
        	   Mockito.when(empSrvcImpl.sortByField(field)).thenReturn(employees);
        	   
               MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emp/sort/field")
 	        		.contentType(MediaType.APPLICATION_JSON_VALUE);
        	 
               MvcResult mvcResult = mockMvc.perform(builder).andReturn(); 
               mockMvc.perform(builder).andExpect(status().isFound());                 
	    }
}


