package com.gradlevig.gradleProject.controller;

import com.gradlevig.gradleProject.model.Employee;
import com.gradlevig.gradleProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- Basic CRUD ---

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // --- Custom Queries ---

    @GetMapping("/department/{dept}")
    public ResponseEntity<List<Employee>> getByDepartment(@PathVariable String dept) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(dept));
    }

    @GetMapping("/salary/{min}")
    public ResponseEntity<List<Employee>> getByMinSalary(@PathVariable Double min) {
        return ResponseEntity.ok(employeeService.getEmployeesWithSalaryGreaterThan(min));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Employee>> searchByName(@PathVariable String name) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(name));
    }

    @GetMapping("/emails")
    public ResponseEntity<List<String>> getEmails() {
        return ResponseEntity.ok(employeeService.getAllEmployeeEmails());
    }

    @GetMapping("/average-salary")
    public ResponseEntity<Double> getAverageSalary() {
        return ResponseEntity.ok(employeeService.getAverageSalary());
    }
}
