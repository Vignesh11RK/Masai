package com.vig.SpringSecurityFinal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // Dummy user data for mock endpoint
    private static final List<Map<String, String>> users = List.of(
            Map.of("username", "admin", "role", "ADMIN"),
            Map.of("username", "librarian", "role", "LIBRARIAN"),
            Map.of("username", "student", "role", "STUDENT"),
            Map.of("username", "guest", "role", "GUEST")
    );

    @GetMapping("/reports")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> getReports() {
        Map<String, Object> report = new HashMap<>();
        report.put("totalBooks", 50);
        report.put("reservedBooks", 12);
        report.put("activeUsers", 4);
        return report;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, String>> getUsers() {
        return users;
    }
}
