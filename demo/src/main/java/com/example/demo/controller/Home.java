package com.example.demo.controller;

import java.util.List;

import org.hibernate.resource.transaction.internal.SynchronizationRegistryStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Utente;
import com.example.demo.services.UtenteService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Home {
	
	@Autowired
	private UtenteService utenteService;
	
    @GetMapping("/")
    public String home(HttpServletRequest request) {
    	Utente u = utenteService.findByIp(getClientIp(request));
    	if (u != null) {
    		request.getSession().setAttribute("user", u);
    		List<Utente> classifica = utenteService.leaderBoard();
    		request.setAttribute("lead", classifica);
    		return "home";
    	}
    	System.out.println(u);
        return "index";
    }
    
    @GetMapping("/register")
    public String reg(HttpSession session) {
    	Utente u = (Utente) session.getAttribute("user");
    	if (u != null) {
    		if (u.getUsername().equalsIgnoreCase("guest")) {
        		session.setAttribute("user", null);
        	}
    	}
    	return "register";
    }
    
    @GetMapping("/start")
    public String start(HttpSession session) {
    	return "play";
    }
    
    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
