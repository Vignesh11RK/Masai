package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";  // This will render the home.html Thymeleaf template
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // This will render the login.html Thymeleaf template
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";  // This will render the admin.html Thymeleaf template
    }

    @GetMapping("/librarian")
    public String librarian() {
        return "librarian";  // This will render the librarian.html Thymeleaf template
    }

    @GetMapping("/student")
    public String student() {
        return "student";  // This will render the student.html Thymeleaf template
    }

    @GetMapping("/guest")
    public String guest() {
        return "guest";  // This will render the guest.html Thymeleaf template
    }
}
