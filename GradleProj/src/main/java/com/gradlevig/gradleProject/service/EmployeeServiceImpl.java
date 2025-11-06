package com.gradlevig.gradleProject.service;

import com.gradlevig.gradleProject.model.Employee;
import com.gradlevig.gradleProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // --- CRUD Methods ---

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }



    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(existing -> {
            existing.setFirstName(updatedEmployee.getFirstName());
            existing.setLastName(updatedEmployee.getLastName());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setDepartment(updatedEmployee.getDepartment());
            existing.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // --- Custom JPQL Queries ---

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.searchByName(name);
    }

    @Override
    public Double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    @Override
    public List<String> getAllEmployeeEmails() {
        return employeeRepository.getAllEmails();
    }
}