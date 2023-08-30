package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

@RestController
@RequestMapping("courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
	
	

}
