package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2ClientApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();

		String baseUrl = "http://localhost:8083";

		String studentsEndpoint = baseUrl + "/students";
		String coursesEndpoint = baseUrl + "/courses";

		String studentsResponse = restTemplate.getForObject(studentsEndpoint, String.class);
		String coursesResponse = restTemplate.getForObject(coursesEndpoint, String.class);

		System.out.println("Students Response:");
		System.out.println(studentsResponse);

		System.out.println("Courses Response:");
		System.out.println(coursesResponse);
	}

}
