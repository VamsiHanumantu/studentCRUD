package com.vamsi.studentmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.vamsi.studentmanagement.model.Student;
import com.vamsi.studentmanagement.repository.StudentRepository;

@RestController
public class StudentController {
   
	@Autowired 
	StudentRepository studentRepo;
	
	@PostMapping("/api/students")
	public void saveStudent(@RequestBody Student student) {
		studentRepo.save(student);
	}
	
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		return new ResponseEntity<List<Student>>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<Student>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student s){
		Optional<Student> student = studentRepo.findById(id);
		
		if(student.isPresent()) {
			student.get().setStudentName(s.getStudentName());
			student.get().setStudentEmail(s.getStudentEmail());
			student.get().setStudentAddress(s.getStudentAddress());
			
			return new ResponseEntity<Student>(studentRepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudents(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
}
