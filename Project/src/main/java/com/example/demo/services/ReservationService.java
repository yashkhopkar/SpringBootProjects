package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private BookService bookService;
	
	public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(String id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }
	
	 public Reservation reserveBook(String bookId, String memberId) {
	        Book book = bookService.findById(bookId).orElseThrow();  // Exception handling needed
	        if (book.isAvailable()) {
	            throw new RuntimeException("Book is available, no need to reserve");  // Better exception handling needed
	        }
	        Reservation reservation = new Reservation();
	        reservation.setBookId(bookId);
	        reservation.setMemberId(memberId);
	        reservation.setReservationDate(LocalDate.now());
	        return reservationRepository.save(reservation);
	    }

}
