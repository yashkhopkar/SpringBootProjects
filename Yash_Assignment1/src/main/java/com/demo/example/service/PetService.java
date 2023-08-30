package com.demo.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.example.entity.Pet;
import com.demo.example.repository.PetRepository;

@Service
public class PetService {
    
	@Autowired
	private PetRepository petRepository;

    
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    
    public Pet getPetById(String id) {
        return petRepository.findById(id).orElse(null);
    }

    
    public void savePet(Pet pet) {
        petRepository.save(pet);
    }
    
    public void updatePet(Pet pet) {
        Pet existingPet = petRepository.findById(pet.getId()).orElse(null);
        if (existingPet != null) {
            existingPet.setName(pet.getName());
            existingPet.setType(pet.getType());
            existingPet.setBreed(pet.getBreed());
            petRepository.save(existingPet);
        }
    }

   
    public void deletePet(String id) {
        petRepository.deleteById(id);
    }
}