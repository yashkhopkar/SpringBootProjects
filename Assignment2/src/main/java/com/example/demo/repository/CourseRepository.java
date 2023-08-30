package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {
	
	 Optional<Course> findByTitle(String title);

}
