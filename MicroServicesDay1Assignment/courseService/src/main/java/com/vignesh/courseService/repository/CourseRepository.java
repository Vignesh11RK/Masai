package com.vignesh.courseService.repository;



import com.vignesh.courseService.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}

