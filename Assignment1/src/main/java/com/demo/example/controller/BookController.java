package com.demo.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.demo.example.model.Book;
import com.demo.example.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
    private BookService bookService;
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE )
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/author/{author}", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Book> findBooksByAuthor(@PathVariable String author) {
    	List<Book> books = this.bookService.findBooksByAuthor(author);
    	return books;
    }

    @GetMapping(value = "/genre/{genre}", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Book> findBooksByGenre(@PathVariable String genre) {
        List<Book> books = this.bookService.findBooksByGenre(genre);
        return books;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_XML_VALUE)
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
    	Optional<Book> existingBook = bookService.getBookById(id);
		if (existingBook.isPresent()) {
			book.setId(id);
			return bookService.addBook(book);
		}
		return null;
        
    }

    @DeleteMapping(value = "/delete/{id}")
    public void removeBook(@PathVariable("id") String id) {
        bookService.removeBook(id);
    }
}

