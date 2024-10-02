package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entities.Utente;
import com.example.demo.services.UtenteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/utente")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping("/add")
	public String add(@ModelAttribute Utente utente, Model model) {
		utenteService.add(utente);
		return "index";
	}
	
	@GetMapping("/guest")
	public String guest(HttpServletRequest request) {
		List<Utente> classifica = utenteService.leaderBoard();
		request.setAttribute("lead", classifica);
		request.getSession().setAttribute("user", new Utente(1000, "guest", "guest", "guest"));
		return "homeguest";
	}
	
	@GetMapping("/login") 
		public String login(@ModelAttribute Utente utente, HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("user") != null) {
			List<Utente> classifica = utenteService.leaderBoard();
			request.setAttribute("lead", classifica);
			return "home";
		}
		try {
			Utente u = utenteService.getByUsernamePassword(utente.getUsername(), utente.getPassword());
			if (u == null) {
				throw new IllegalArgumentException("Utente non trovato");
			}
			List<Utente> classifica = utenteService.leaderBoard();
			request.setAttribute("lead", classifica);
			session.setAttribute("user", u);
			return "home";
		} catch (Exception e) {
			request.setAttribute("trovato", "no");
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "index";
	}
	
}
