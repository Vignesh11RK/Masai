package com.vig.employeesystem.service;

import com.vig.employeesystem.entity.Employee;

import java.util.List;


public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee updatedEmployee);
    void deleteEmployee(Long id);
}