package com.gradlevig.gradleProject.service;

import com.gradlevig.gradleProject.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // CRUD operations
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);

    // Custom JPQL queries
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getEmployeesWithSalaryGreaterThan(Double salary);
    List<Employee> searchEmployeesByName(String name);
    Double getAverageSalary();
    List<String> getAllEmployeeEmails();
}
