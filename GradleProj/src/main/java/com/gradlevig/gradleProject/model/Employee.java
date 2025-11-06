package com.gradlevig.gradleProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // changed from int to Long

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String department;

    private Double salary;  // changed from String to Double
}