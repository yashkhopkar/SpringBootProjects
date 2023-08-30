package com.demo.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.example.entity.Pet;
import com.demo.example.entity.PetType;
import com.demo.example.service.PetService;

@SpringBootApplication
public class YashAssignment1Application {
	
	@Autowired
	private PetService petService;
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(YashAssignment1Application.class, args);
	}
	
    @Bean
    public CommandLineRunner initDatabase(PetService petService) {
        return args -> {
        	petService.savePet(Pet.builder().name("Max").type(PetType.DOG).breed("Labrador Retriever").build());
        	petService.savePet(Pet.builder().name("Whiskers").type(PetType.CAT).breed("Persian").build());
        	petService.savePet(Pet.builder().name("Tweety").type(PetType.BIRD).breed("Canary").build());
        	petService.savePet(Pet.builder().name("Buddy").type(PetType.DOG).breed("Golden Retriever").build());
        	petService.savePet(Pet.builder().name("Fluffy").type(PetType.DOG).breed("Siamese").build());
        };
    }

}