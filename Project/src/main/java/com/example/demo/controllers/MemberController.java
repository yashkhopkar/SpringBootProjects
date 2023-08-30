package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Member;
import com.example.demo.services.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public String listMembers(Model model) {
	    model.addAttribute("members", memberService.findAll());
	    return "member/list";
	}

	// Render the form for adding a new member
	@GetMapping("/members/add")
	public String addMemberForm(Model model) {
	    model.addAttribute("member", new Member());
	    return "member/add";
	}

	// Handle the form submission for adding a new member
	@PostMapping("/members/add")
	public String addMemberSubmit(Member member) {
		member.setRole("USER");
	    memberService.save(member);
	    return "redirect:/members";
	}

	@GetMapping("/members/edit/{id}")
	public String editMember(@PathVariable String id, Model model) {
	    model.addAttribute("member", memberService.findById(id));
	    return "member/edit";
	}

	@PostMapping("/members/update")
	public String updateMember(Member member) {
	    memberService.save(member);
	    return "redirect:/members";
	}

	@GetMapping("/members/delete/{id}")
	public String deleteMember(@PathVariable String id) {
	    memberService.deleteById(id);
	    return "redirect:/members";
	}

}
