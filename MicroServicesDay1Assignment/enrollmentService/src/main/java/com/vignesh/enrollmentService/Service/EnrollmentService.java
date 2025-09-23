package com.vignesh.enrollmentService.Service;


import com.vignesh.enrollmentService.Entity.Enrollment;
import com.vignesh.enrollmentService.Exception.ResourceNotFoundException;
import com.vignesh.enrollmentService.Repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final RestTemplate restTemplate;

    private static final String STUDENT_SERVICE_URL = "http://localhost:8081/students/";
    private static final String COURSE_SERVICE_URL = "http://localhost:8082/courses/";

    public EnrollmentService(EnrollmentRepository enrollmentRepository, RestTemplate restTemplate) {
        this.enrollmentRepository = enrollmentRepository;
        this.restTemplate = restTemplate;
    }

    public Enrollment enroll(Long studentId, Long courseId) {
        // Check student exists
        try {
            restTemplate.getForObject(STUDENT_SERVICE_URL + studentId, Object.class);
        } catch (RestClientException e) {
            throw new ResourceNotFoundException("Student not found");
        }

        // Check course exists
        try {
            restTemplate.getForObject(COURSE_SERVICE_URL + courseId, Object.class);
        } catch (RestClientException e) {
            throw new ResourceNotFoundException("Course not found");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        return enrollmentRepository.save(enrollment);
    }

    public List<Object> getEnrolledCourses(Long studentId) {
        // Check student exists
        try {
            restTemplate.getForObject(STUDENT_SERVICE_URL + studentId, Object.class);
        } catch (RestClientException e) {
            throw new ResourceNotFoundException("Student not found");
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(enroll -> restTemplate.getForObject(COURSE_SERVICE_URL + enroll.getCourseId(), Object.class))
                .collect(Collectors.toList());
    }
}

