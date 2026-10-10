package com.example.studentmanagementbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagementbackend.entity.Student;
import com.example.studentmanagementbackend.repository.StudentRepository;

@RestController
@RequestMapping("/api/admin/students")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class AdminController {

    private final StudentRepository repository;

    public AdminController(StudentRepository repository) {
        this.repository = repository;
    }

    // View all students
    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    // Add student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        calculateGrade(student);

        return repository.save(student);
    }

    // Update student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        existing.setStudentId(student.getStudentId());
        existing.setName(student.getName());
        existing.setBranch(student.getBranch());
        existing.setMarks(student.getMarks());

        calculateGrade(existing);

        return repository.save(existing);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        repository.deleteById(id);

        return "Student deleted successfully";
    }

    // Grade calculation
    private void calculateGrade(Student student) {

        int marks = student.getMarks();

        if (marks >= 90) {
            student.setGrade("A+");
        } else if (marks >= 80) {
            student.setGrade("A");
        } else if (marks >= 70) {
            student.setGrade("B");
        } else if (marks >= 60) {
            student.setGrade("C");
        } else if (marks >= 50) {
            student.setGrade("D");
        } else {
            student.setGrade("F");
        }
    }
}