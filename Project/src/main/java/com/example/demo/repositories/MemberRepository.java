package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
	
	 Optional<Member> findByMembershipId(String membershipId);

}
