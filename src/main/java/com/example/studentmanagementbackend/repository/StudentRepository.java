package com.example.studentmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanagementbackend.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}