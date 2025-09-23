package com.vignesh.enrollmentService.Controller;

import com.vignesh.enrollmentService.Entity.Enrollment;
import com.vignesh.enrollmentService.Service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<?> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        if (studentId == null || courseId == null || studentId <= 0 || courseId <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid studentId or courseId"));
        }
        Enrollment enrollment = enrollmentService.enroll(studentId, courseId);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }

    @GetMapping("/enrollments/{studentId}")
    public ResponseEntity<?> getEnrollments(@PathVariable Long studentId) {
        if (studentId == null || studentId <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid studentId"));
        }
        List<Object> courses = enrollmentService.getEnrolledCourses(studentId);

        // Build response as per example scenario
        return ResponseEntity.ok(Map.of(
                "studentId", studentId,
                "enrolledCourses", courses
        ));
    }
}

