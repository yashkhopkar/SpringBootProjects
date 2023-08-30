package com.demo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.example.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
}
