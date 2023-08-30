package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

}
