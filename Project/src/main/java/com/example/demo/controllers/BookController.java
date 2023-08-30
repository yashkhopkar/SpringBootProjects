package com.example.demo.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Book;
import com.example.demo.models.Member;
import com.example.demo.services.BookService;
import com.example.demo.services.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/home")
	public String home() {
	    return "home";
	}
	
	@GetMapping("/books")
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "book/list";
	}

	@PostMapping("/books/search")
	public String searchBooks(String title, Model model) {
		model.addAttribute("books", bookService.searchByTitle(title));
		return "book/list";
	}

	@GetMapping("/books/add")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book());
		return "book/add";
	}

	@PostMapping("/books/add")
	public String addBookSubmit(Book book) {
		book.setAvailable(true);
		bookService.save(book);
		return "redirect:/books";
	}

	@GetMapping("/books/edit/{id}")
	public String editBook(@PathVariable String id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		return "book/edit";
	}

	@PostMapping("/books/update")
	public String updateBook(Book book) {
		bookService.save(book);
		return "redirect:/books";
	}

	@GetMapping("/books/delete/{id}")
	public String deleteBook(@PathVariable String id) {
		bookService.deleteById(id);
		return "redirect:/books";
	}
	
//	@GetMapping("/books/borrow/{id}")
//	public String borrowBook(@PathVariable String id, Authentication authentication) {
//	    Book book = bookService.findById(id).orElseThrow(); // handle the case when the book is not found
//	    Member member = memberService.findByMembershipId(authentication.getName()).orElseThrow(); // Assuming the membershipId is used as the username
//	    
////	    if (book.isAvailable()) {
//	        book.setBorrowedByMemberId(member.getId());
//	        book.setBorrowedDate(new Date());
//	        Calendar c = Calendar.getInstance();
//	        c.setTime(new Date());
//	        c.add(Calendar.DATE, 14);
//	        book.setDueDate(c.getTime());
//	        //book.setIsAvailable(false);
//	        
//	        member.getBorrowedBooks().add(book.getId());
//	        
//	        bookService.save(book);
//	        memberService.save(member);
////	    }
//	    return "redirect:/books";
//	}
	
	
	@GetMapping("/books/return/{id}")
	public String returnBook(@PathVariable String id, Authentication authentication) {
	    Book book = bookService.findById(id).orElseThrow();
	    Member member = memberService.findByMembershipId(authentication.getName()).orElseThrow();
	    
	    if (!book.isAvailable() && member.getId().equals(book.getBorrowedByMemberId())) {
	        book.setBorrowedByMemberId(null);
	        book.setBorrowedDate(null);
	        book.setDueDate(null);
//	        book.setIsAvailable(true);
	        
	        member.getBorrowedBooks().remove(book.getId());
	        
	        bookService.save(book);
	        memberService.save(member);
	    }
	    return "redirect:/books";
	}
	
	@GetMapping("/user/books")
	public String listUserBooks(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "user/books";
	}
	
	
}
