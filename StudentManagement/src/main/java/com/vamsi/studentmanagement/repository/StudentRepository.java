package com.vamsi.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vamsi.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
 
}
