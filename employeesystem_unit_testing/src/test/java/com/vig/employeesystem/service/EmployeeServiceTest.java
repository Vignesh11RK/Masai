package com.vig.employeesystem.service;
import com.vig.employeesystem.entity.Employee;
import com.vig.employeesystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getEmployeeById_found_returnsEmployee() {
        Employee employee = new Employee(1L, "John", "HR");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("HR", result.getDepartment());
        verify(employeeRepository).findById(1L);
    }

    @Test
    public void getEmployeeById_notFound_throwsException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.getEmployeeById(1L));
        verify(employeeRepository).findById(1L);
    }

    @Test
    public void getEmployeesByDepartment_returnsCorrectList() {
        List<Employee> employees = List.of(
                new Employee(1L, "Alice", "IT"),
                new Employee(2L, "Bob", "IT")
        );
        when(employeeRepository.findByDepartment("IT")).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesByDepartment("IT");

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        verify(employeeRepository).findByDepartment("IT");
    }
}
