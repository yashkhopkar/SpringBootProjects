package com.example.demo.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
    
    public List<Book> searchByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title, "i"));
        return mongoTemplate.find(query, Book.class);
    }
    
    public Double calculateFine(Book book) {
        if (book.getDueDate() == null || book.isAvailable()) {
            return 0.0;
        }

        long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate().toInstant(), Instant.now());
        if (daysOverdue > 0) {
            return daysOverdue * 1.0;
        }
        return 0.0;
    }

    

}
