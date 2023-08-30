package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Reservation;
import com.example.demo.services.ReservationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reservations")
	public String listReservations(Model model) {
	    model.addAttribute("reservations", reservationService.findAll());
	    return "reservation/list";
	}

	@PostMapping("/reservations/add")
	public String addReservation(Reservation reservation) {
	    reservationService.save(reservation);
	    return "redirect:/reservations";
	}

	@GetMapping("/reservations/edit/{id}")
	public String editReservation(@PathVariable String id, Model model) {
	    model.addAttribute("reservation", reservationService.findById(id).orElse(new Reservation()));
	    return "reservation/edit";
	}

	@PostMapping("/reservations/update")
	public String updateReservation(Reservation reservation) {
	    reservationService.save(reservation);
	    return "redirect:/reservations";
	}

	@PostMapping("/reservations/delete/{id}")
	public String deleteReservation(@PathVariable String id) {
	    reservationService.deleteById(id);
	    return "redirect:/reservations";
	}
	
//	@GetMapping("/logout")
//    public String logout(HttpServletRequest request) throws ServletException {
//		request.getSession().invalidate();
//        return "home";
//    }
}
