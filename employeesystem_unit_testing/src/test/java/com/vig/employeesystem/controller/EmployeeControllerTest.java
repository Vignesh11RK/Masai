package com.vig.employeesystem.controller;

import com.vig.employeesystem.entity.Employee;
import com.vig.employeesystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = new Employee(1L, "John", "IT");
    }

    @Test
    public void testGetEmployeeById_Success() throws Exception {
        when(service.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    public void testGetEmployeeById_NotFound() throws Exception {
        when(service.getEmployeeById(1L)).thenThrow(new RuntimeException("Employee not found"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEmployeesByDepartment() throws Exception {
        when(service.getEmployeesByDepartment("IT")).thenReturn(Arrays.asList(
                new Employee(1L, "John", "IT"),
                new Employee(2L, "Jane", "IT")
        ));

        mockMvc.perform(get("/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }
}

