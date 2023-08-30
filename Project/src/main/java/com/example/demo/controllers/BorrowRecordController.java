package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.BorrowRecord;
import com.example.demo.services.BorrowRecordService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BorrowRecordController {

	@Autowired
	private BorrowRecordService borrowRecordService;

	@GetMapping("/borrow-records")
	public String listBorrowRecords(Model model) {
		model.addAttribute("borrowRecords", borrowRecordService.findAll());
		return "borrowRecord/list";
	}

	@GetMapping("/borrow-records/borrow")
	public String borrowBook(String bookId, String memberId) {
		borrowRecordService.borrowBook(bookId, memberId);
		return "redirect:/borrow-records";
	}

	@GetMapping("/borrow-records/return/{id}")
	public String returnBook(@PathVariable String id) {
		borrowRecordService.returnBook(id);
		return "redirect:/borrow-records";
	}
	
//	@GetMapping("/logout")
//    public String logout(HttpServletRequest request) throws ServletException {
//		request.getSession().invalidate();
//        return "home";
//    }
}
