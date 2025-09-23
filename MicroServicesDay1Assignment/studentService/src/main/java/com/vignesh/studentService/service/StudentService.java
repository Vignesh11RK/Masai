package com.vignesh.studentService.service;


import com.vignesh.studentService.dto.StudentDTO;
import com.vignesh.studentService.entity.Student;
import com.vignesh.studentService.exception.ResourceNotFoundException;
import com.vignesh.studentService.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentDTO addStudent(StudentDTO dto) {
        Student student = mapper.map(dto, Student.class);
        Student saved = repository.save(student);
        return mapper.map(saved, StudentDTO.class);
    }

    public StudentDTO getStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return mapper.map(student, StudentDTO.class);
    }

    public List<StudentDTO> getAllStudents() {
        return repository.findAll().stream().map(s -> mapper.map(s, StudentDTO.class)).collect(Collectors.toList());
    }
}

