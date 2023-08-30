package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryRepository extends JpaRepository<Accessory, String> {

	List<Accessory> findByCategoryName(String categoryName);
}
