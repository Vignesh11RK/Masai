package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<> ();

    public StudentController(){
        students.add(new Student("Soham",45,3));
        students.add(new Student("Soh",65,4));
        students.add(new Student("Vignem",55,2));
    }

    @GetMapping("/students")
public List<Student> students(){
        return students;
}


}
