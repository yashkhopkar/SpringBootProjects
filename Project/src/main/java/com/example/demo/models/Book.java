package com.example.demo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Book {
	
	@Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private String borrowedByMemberId;
    private Date borrowedDate;
    private Date dueDate; 

}
