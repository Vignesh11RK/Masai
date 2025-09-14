package com.vig.employeesystem.service;

import com.vig.employeesystem.entity.Employee;
import com.vig.employeesystem.repository.EmployeeRepository;
import com.vig.employeesystem.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = new Employee(1L, "John Doe", "IT", 5000.0);
    }

    @Test
    void createEmployee_validInput_success() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.createEmployee(employee);

        assertEquals("John Doe", saved.getName());
        assertEquals(5000.0, saved.getSalary());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void createEmployee_nullName_throwsException() {
        employee.setName(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> employeeService.createEmployee(employee));

        assertEquals("Employee name cannot be null or empty", ex.getMessage());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void createEmployee_invalidSalary_throwsException() {
        employee.setSalary(0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> employeeService.createEmployee(employee));

        assertEquals("Salary must be greater than zero", ex.getMessage());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void getEmployeeById_validId_success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("John Doe", result.getName());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeById_invalidId_throwsException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.getEmployeeById(1L));
        verify(employeeRepository).findById(1L);
    }

    @Test
    void updateEmployee_validId_success() {
        Employee updated = new Employee(null, "Jane Doe", "HR", 6000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertEquals("Jane Doe", result.getName());
        assertEquals("HR", result.getDepartment());
        assertEquals(6000.0, result.getSalary());
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateEmployee_invalidId_throwsException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.updateEmployee(1L, employee));
        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void deleteEmployee_validId_success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteEmployee_invalidId_throwsException() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> employeeService.deleteEmployee(1L));

        verify(employeeRepository).existsById(1L);
        verify(employeeRepository, never()).deleteById(any());
    }
}