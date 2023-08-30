package com.example.demo.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.models.BorrowRecord;
import com.example.demo.repositories.BorrowRecordRepository;

@Service
public class BorrowRecordService {

	@Autowired
	private BorrowRecordRepository borrowRecordRepository;
	
	@Autowired
	private BookService bookService;
	
	private static final int MAX_BORROW_DAYS = 14;
	private static final double DAILY_FINE = 1.0;

	public List<BorrowRecord> findAll() {
		return borrowRecordRepository.findAll();
	}

	public Optional<BorrowRecord> findById(String id) {
		return borrowRecordRepository.findById(id);
	}

	public BorrowRecord save(BorrowRecord borrowRecord) {
		return borrowRecordRepository.save(borrowRecord);
	}

	public void deleteById(String id) {
		borrowRecordRepository.deleteById(id);
	}

	public BorrowRecord borrowBook(String bookId, String memberId) {
		Book book = bookService.findById(bookId).orElseThrow();
		if (!book.isAvailable()) {
			throw new RuntimeException("Book not available");
		}
		book.setAvailable(false);
		bookService.save(book);
		BorrowRecord record = new BorrowRecord();
		record.setBookId(bookId);
		record.setMemberId(memberId);
		record.setBorrowedDate(LocalDate.now());
		record.setDueDate(LocalDate.now().plusDays(MAX_BORROW_DAYS));
		return borrowRecordRepository.save(record);
	}

	public BorrowRecord returnBook(String recordId) {
		BorrowRecord record = borrowRecordRepository.findById(recordId).orElseThrow();
		record.setReturnDate(LocalDate.now());
		long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), record.getReturnDate());
		if (daysLate > 0) {
			record.setFine(daysLate * DAILY_FINE);
		}
		return borrowRecordRepository.save(record);
	}
}
