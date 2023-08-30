package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Member;
import com.example.demo.repositories.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void deleteById(String id) {
        memberRepository.deleteById(id);
    }
    
    public Optional<Member> findByMembershipId(String membershipId) {
        return memberRepository.findByMembershipId(membershipId);
    }

}
