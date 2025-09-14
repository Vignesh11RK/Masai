package com.vig.employeesystem.repository;


import com.vig.employeesystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
