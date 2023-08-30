package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FinalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalExamApplication.class, args);
	}

	@Autowired
	private AccessoryRepository accessoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/accessories")
	public List<Accessory> getAllAccessories() {
		return accessoryRepository.findAll();
	}

	@GetMapping("/accessories/by-category")
	public List<Accessory> getAccessoriesByCategory(@RequestParam String categoryName) {
		return accessoryRepository.findByCategoryName(categoryName);
	}

	@PostMapping("/accessories")
	public Accessory postAccessory(@RequestBody Accessory accessory) {
		Category category = categoryRepository.findByName(accessory.getCategory().getName());
		if (category == null) {
			category = categoryRepository.save(accessory.getCategory());
		}
		accessory.setCategory(category);
		return accessoryRepository.save(accessory);
	}

}
