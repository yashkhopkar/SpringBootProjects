package com.demo.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.example.model.Book;
import com.demo.example.repository.BookRepository;
import com.demo.example.service.BookService;

@SpringBootApplication
public class Assignment1_Yash {
    public static void main(String[] args) {
        SpringApplication.run(Assignment1_Yash.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(BookService bookService) {
        return args -> {
            bookService.addBook(Book.builder().name("The Great Gatsby").price(10.99).genre("Fiction").author("F. Scott Fitzgerald").build());
            bookService.addBook(Book.builder().name("To Kill a Mockingbird").price(9.99).genre("Fiction").author("Harper Lee").build());
            bookService.addBook(Book.builder().name("1984").price(8.99).genre("Fiction").author("George Orwell").build());
            bookService.addBook(Book.builder().name("The Hobbit").price(12.99).genre("Fantasy").author("J.R.R. Tolkien").build());
            bookService.addBook(Book.builder().name("Pride and Prejudice").price(7.99).genre("Fiction").author("Jane Austen").build());
        };
    }
}
