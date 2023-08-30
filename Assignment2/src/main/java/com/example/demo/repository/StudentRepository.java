package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
	
	  @Query("SELECT s.id as studentId, s.name as studentName, c.id as courseId, c.title as courseTitle FROM Student s JOIN s.courses c")
	    List<Object[]> findStudentsWithCourses();

}
