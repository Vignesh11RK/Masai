package com.gradlevig.gradleProject.repository;

import com.gradlevig.gradleProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 1. Find employees by department
    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(@Param("department") String department);

    // 2. Find employees with salary greater than given amount
    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary")
    List<Employee> findBySalaryGreaterThan(@Param("minSalary") Double minSalary);

    // 3. Search employees by partial name match (first or last name)
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(@Param("name") String name);

    // 4. Get average salary of all employees
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    // 5. Fetch all employee emails
    @Query("SELECT e.email FROM Employee e")
    List<String> getAllEmails();


    void deleteById(Long id);
}
