package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
