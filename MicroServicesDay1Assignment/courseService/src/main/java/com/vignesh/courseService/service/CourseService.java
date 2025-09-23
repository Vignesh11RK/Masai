package com.vignesh.courseService.service;


import com.vignesh.courseService.dto.CourseDTO;
import com.vignesh.courseService.entity.Course;
import com.vignesh.courseService.exception.ResourceNotFoundException;
import com.vignesh.courseService.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public CourseDTO addCourse(CourseDTO dto) {
        Course course = mapper.map(dto, Course.class);
        Course saved = repository.save(course);
        return mapper.map(saved, CourseDTO.class);
    }

    public CourseDTO getCourse(Long id) {
        Course course = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return mapper.map(course, CourseDTO.class);
    }

    public List<CourseDTO> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(c -> mapper.map(c, CourseDTO.class))
                .collect(Collectors.toList());
    }
}
