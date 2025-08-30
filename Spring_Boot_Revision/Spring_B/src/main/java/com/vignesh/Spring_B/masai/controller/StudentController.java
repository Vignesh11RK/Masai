package com.vignesh.Spring_B.masai.controller;

import com.vignesh.Spring_B.masai.model.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController //entry point are here

public class StudentController {
    private List<Student> students=new ArrayList<>();



    public StudentController() {
        students.add(new Student(17,"Ram",800));
        students.add(new Student(11,"Ratan",900));
        students.add(new Student(12,"Soham",908));
        students.add(new Student(10,"Udav",909));
    }


    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }
// returning info of particular student with roll no
    @GetMapping("/students/{roll}")
    public ResponseEntity<List<Student>> getStudentByRollNo(@PathVariable("roll") Integer roll){  // student is the custom data type created
           List<Student> list=students.stream()
                   .filter(s->s.getRoll()==roll)
                   .collect(Collectors.toList());

           if(list.size()==0){
            throw new IllegalArgumentException("Student Does not exist" +roll);
        }
        HttpHeaders hh=new HttpHeaders();
           hh.add("jwt","vigvigvigvig");
           hh.add("user","admin");


           return new ResponseEntity<List<Student>>(list,hh,HttpStatus.OK);
    }

    @PostMapping("/students")
     public ResponseEntity<String> addStudent(@RequestBody Student student){
        students.add(student);
        return new ResponseEntity<String>("Student added sucessfullly",HttpStatus.OK);
    }

    @PutMapping("/students/{roll}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("roll") Integer roll) {
        for (Student st : students) {
            if (st.getRoll()==roll) {
                st.setName(student.getName());
                st.setMarks(student.getMarks());
                return new ResponseEntity<Student>(st,HttpStatus.OK);
            }
        }

        // If student not found
      throw new IllegalArgumentException("Student not found");
    }

// without response entity

//    @DeleteMapping("students/{roll}")
//    public String deleteByRoll(@PathVariable("roll")Integer roll){
//     boolean flag = students.removeIf(s->s.getRoll()==roll);
//     if(flag){
//         System.out.println("Student deleted sucesfully ");
//     }
//     else {
//         throw new IllegalArgumentException("Student not found");
//     }
//    }


    // with response entity

    @DeleteMapping("students/{roll}")
    public ResponseEntity<String> deleteByRoll(@PathVariable("roll") Integer roll){
        boolean flag=students.removeIf(s->s.getRoll().equals(roll));
        // this came as true
        if(flag) {
            return ResponseEntity.ok("Student deleted sucessfully");
        }
        else {
            throw new IllegalArgumentException("Students not found");
        }
    }





}
