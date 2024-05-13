package com.mkpits.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mkpits.model.Address;
import com.mkpits.model.Employee;
import com.mkpits.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    
    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testCreateEmployee_Success() throws Exception {
        Employee mockEmployee = new Employee();
        mockEmployee.setName("patel");
        mockEmployee.setEmail("patel@gmail.com");
        mockEmployee.setNumber(9829898895L);
        mockEmployee.setSalary(88000);
        Address address1 = new Address("nikol", "Ahmedabad", "Gujarat", "gangotri circle");
        Address address2 = new Address("visavadar", "Amreli", "Gujarat", "khambha gir");
        mockEmployee.setAddresses(Arrays.asList(address1, address2));

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployee);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        String requestJson = "{\n" +
                "    \"name\": \"patel\",\n" +
                "    \"email\": \"patel@gmail.com\",\n" +
                "    \"number\": 9829898895,\n" +
                "    \"salary\": 88000,\n" +
                "    \"addresses\": [\n" +
                "        {\n" +
                "            \"street\": \"nikol\",\n" +
                "            \"city\": \"Ahmedabad\",\n" +
                "            \"state\": \"Gujarat\",\n" +
                "            \"landmark\": \"gangotri circle\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"street\": \"visavadar\",\n" +
                "            \"city\": \"Amreli\",\n" +
                "            \"state\": \"Gujarat\",\n" +
                "            \"landmark\": \"khambha gir\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateEmployee_InvalidRequest() throws Exception {
        String requestJson = "{\"email\":\"hast@test.com\",\"number\":1111144444,\"salary\":35000}";

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());

        verify(employeeService, never()).createEmployee(any(Employee.class));
    }
}
