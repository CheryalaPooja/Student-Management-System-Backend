package com.example.studentmanagementbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagementbackend.entity.Student;
import com.example.studentmanagementbackend.repository.StudentRepository;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class StudentDashboardController {

    private final StudentRepository repository;

    public StudentDashboardController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/profile/{studentId}")
    public Student getStudentProfile(@PathVariable String studentId) {

        return repository.findAll()
                .stream()
                .filter(student ->
                        student.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }
}
