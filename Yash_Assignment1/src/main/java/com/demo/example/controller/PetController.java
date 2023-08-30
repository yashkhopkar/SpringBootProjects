package com.demo.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.example.entity.Pet;
import com.demo.example.entity.PetType;
import com.demo.example.service.PetService;

@Controller
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping("/")
	public String home(Model model) {
		List<Pet> pets = petService.getAllPets();
		model.addAttribute("pets", pets);
		return "home";
	}

	@GetMapping("/add")
	public String addPet(Model model) {
		model.addAttribute("petTypes", PetType.values());
		model.addAttribute("pet", new Pet());
		return "add-pet";
	}

	@PostMapping("/add")
	public String savePet(@ModelAttribute("pet") Pet pet) {
		petService.savePet(pet);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String editPet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPetById(id);
		model.addAttribute("pet", pet);
		return "edit-pet";
	}

	@PostMapping("/update")
	public String updatePet(@ModelAttribute("pet") Pet pet) {
		petService.updatePet(pet);

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deletePet(@PathVariable("id") String id) {
		petService.deletePet(id);
		return "redirect:/";
	}
}
