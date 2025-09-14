package com.example.Spring_Boot_A.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @PostMapping("/register")
    public Student(){

    }
}
