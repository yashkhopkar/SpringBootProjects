package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.BorrowRecord;

@Repository
public interface BorrowRecordRepository extends MongoRepository<BorrowRecord, String> {

}
