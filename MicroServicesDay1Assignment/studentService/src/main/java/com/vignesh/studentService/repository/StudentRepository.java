package com.vignesh.studentService.repository;


import com.vignesh.studentService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}