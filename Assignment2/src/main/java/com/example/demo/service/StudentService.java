package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	 public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    public Student getStudentById(String id) {
	        return studentRepository.findById(id).orElse(null);
	    }

	    public Student saveStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    public void deleteStudent(String id) {
	        studentRepository.deleteById(id);
	    }
	    
	    public List<Object[]> getStudentsWithCourses() {
	        return studentRepository.findStudentsWithCourses();
	    }
	    
	    public Student saveStudentWithCourses(Student student) {
	        for (int i = 0; i < student.getCourses().size(); i++) {
	            Course course = student.getCourses().get(i);
	            
	            // Check by course title
	            Optional<Course> existingCourse = courseRepository.findByTitle(course.getTitle());
	            
	            if (existingCourse.isPresent()) {
	                student.getCourses().set(i, existingCourse.get());
	            } else {
	                courseRepository.save(course);
	            }
	        }
	        return studentRepository.save(student);
	    }

}
