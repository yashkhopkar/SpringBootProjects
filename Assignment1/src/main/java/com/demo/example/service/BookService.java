package com.demo.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.example.model.Book;
import com.demo.example.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
	@Autowired
	private BookRepository bookRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(String id){
		return this.bookRepository.findById(id);
	}

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(String id) {
        bookRepository.deleteById(id);
    }
}

