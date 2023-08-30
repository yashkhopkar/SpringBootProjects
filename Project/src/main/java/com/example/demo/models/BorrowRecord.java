package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecord {

	@Id
	private String id;
	private String bookId;
	private String memberId;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double fine;

}
