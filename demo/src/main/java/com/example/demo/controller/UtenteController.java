package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Utente;
import com.example.demo.services.UtenteService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/utente")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping("/add")
	public String add(@ModelAttribute Utente utente, HttpServletRequest request) {
		try {
			if (utente.getUsername().equalsIgnoreCase("guest")) {
				throw new Exception("username non valido");
			}
			utenteService.add(utente);
		} catch(Exception e) {
			request.setAttribute("err", "si");
			return "/register";
		}
		return "index";
	}
	
	@GetMapping("/guest")
	public String guest(HttpServletRequest request) {
		List<Utente> classifica = utenteService.leaderBoard();
		request.setAttribute("lead", classifica);
		request.getSession().setAttribute("user", new Utente(1000, "guest", "guest"));
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
			if (u.getOnline()) {
				throw new IllegalArgumentException("Utente online");
			}
			utenteService.updateIpSessionId(u.getId(), session.getId(), getClientIp(request));
			List<Utente> classifica = utenteService.leaderBoard();
			request.setAttribute("lead", classifica);
			session.setAttribute("user", utenteService.update(u.getId(), true).get());
			return "home";
		} catch (Exception e) {
			request.setAttribute("trovato", "no");
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		Utente u = (Utente) session.getAttribute("user");
		if (u.getUsername().equalsIgnoreCase("guest")) {
			session.setAttribute("user", null);
			session.invalidate();
			return "index";
		}
		u.setOnline(false);
		u.setIp(null);
		utenteService.add(u);
		session.setAttribute("user", null);
		session.invalidate();
		return "index";
	}
	
	 public String getClientIp(HttpServletRequest request) {
	        String ip = request.getHeader("X-Forwarded-For");
	        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	    }
	 
	 @PostMapping("/ricarica")
	 public String ricarica(HttpSession session) {
		 Utente u = (Utente)session.getAttribute("user");
		 int id = u.getId();
		 utenteService.updateSaldo(id, 1000);
		 u.setSaldo(1000);
		 session.setAttribute("user", u);
		 return "play";
	 }
}
