package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController

@RequestMapping("/api/students") // ใช้ /students เป็น base URL

public class StudentController {

@Autowired

private StudentRepository studentRepository;

// Create a new student

@PostMapping

public ResponseEntity<Student> addStudent(@RequestBody Student student) {

try {

Student savedStudent = studentRepository.save(student);

return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

} catch (Exception e) {

return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

}

}

// Get all students

@GetMapping

public List<Student> getAllStudents() {

return studentRepository.findAll();

}

// Get student by id

@GetMapping("/{id}")

public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

Optional<Student> student = studentRepository.findById(id);

if (student.isPresent()) {

return new ResponseEntity<>(student.get(), HttpStatus.OK);

} else {

return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}

}



}