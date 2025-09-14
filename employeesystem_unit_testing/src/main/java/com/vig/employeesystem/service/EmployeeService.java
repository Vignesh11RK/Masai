package com.vig.employeesystem.service;

import com.vig.employeesystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    List<Employee> getEmployeesByDepartment(String department);
}