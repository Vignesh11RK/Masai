package com.vignesh.Spring_B.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Student {
    private Integer roll;
    private String name;
    private Integer marks;


//  we can use lombok no args instead of writing all this
//    public Student(Integer roll, String name, Integer marks) {
//        this.roll = roll;
//        this.name = name;
//        this.marks = marks;
//    }
//
//    public Student() {
//    }
//
//    public Integer getroll() {
//        return roll;
//    }
//
//    public void setroll(Integer roll) {
//        this.roll = roll;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getMarks() {
//        return marks;
//    }
//
//    public void setMarks(Integer marks) {
//        this.marks = marks;
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "roll=" + roll +
//                ", name='" + name + '\'' +
//                ", marks=" + marks +
//                '}';
//    }



}
