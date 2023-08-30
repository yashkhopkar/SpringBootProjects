package com.demo.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.example.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
}
