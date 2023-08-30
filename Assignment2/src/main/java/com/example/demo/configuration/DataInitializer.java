package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

@Configuration
public class DataInitializer {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Bean
	public CommandLineRunner initData() {
		return args -> {

			Course course1 = new Course();
            course1.setTitle("Math");
            Course course2 = new Course();
            course2.setTitle("History");

            courseRepository.saveAll(Arrays.asList(course1, course2));

            Student student1 = new Student();
            student1.setName("Alice");
            student1.getCourses().add(course1);
            student1.getCourses().add(course2);

            Student student2 = new Student();
            student2.setName("Bob");
            student2.getCourses().add(course1);

            Student student3 = new Student();
            student3.setName("Charlie");
            student3.getCourses().add(course2);

            Student student4 = new Student();
            student4.setName("David");
            student4.getCourses().add(course1);
            student4.getCourses().add(course2);

            studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));
		};

	};

}
