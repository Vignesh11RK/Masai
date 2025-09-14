package com.vig.employeesystem.controller;

import com.vig.employeesystem.entity.Employee;
import com.vig.employeesystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
   private EmployeeService employeeService;

    @Test
    public void getEmployeeById_found_returnsJson() throws Exception {
        Employee employee = new Employee(1L, "John Doe", "Finance");
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.department", is("Finance")));
    }

    @Test
    public void getEmployeeById_notFound_returns404() throws Exception {
        Mockito.when(employeeService.getEmployeeById(anyLong()))
                .thenThrow(new NoSuchElementException("Not found"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployeesByDepartment_returnsJsonList() throws Exception {
        List<Employee> employees = List.of(
                new Employee(1L, "Alice", "IT"),
                new Employee(2L, "Bob", "IT")
        );

        Mockito.when(employeeService.getEmployeesByDepartment("IT")).thenReturn(employees);

        mockMvc.perform(get("/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Alice")))
                .andExpect(jsonPath("$[1].name", is("Bob")));
    }
}
