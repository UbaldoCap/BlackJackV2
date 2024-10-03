package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Utente;

import jakarta.servlet.http.HttpSession;

@Controller
public class Home {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/register")
    public String reg(HttpSession session) {
    	Utente u = (Utente) session.getAttribute("user");
    	if (u != null) {
    		if (u.getNome().equalsIgnoreCase("guest") & u.getPassword().equalsIgnoreCase("guest") & u.getUsername().equalsIgnoreCase("guest")) {
        		session.setAttribute("user", null);
        	}
    	}
    	return "register";
    }
    
    @GetMapping("/start")
    public String start() {
    	return "play";
    }
}
