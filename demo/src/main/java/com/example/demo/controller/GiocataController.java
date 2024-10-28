package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Giocata;
import com.example.demo.entities.Utente;
import com.example.demo.services.GiocataService;
import com.example.demo.services.UtenteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/g")
public class GiocataController {
	
	@Autowired 
	private GiocataService gs;
	
	@Autowired
	private UtenteService us;
	
	/*
	@GetMapping
	public void add(HttpServletRequest request) {
		Utente utente = (Utente) request.getSession().getAttribute("user");
		int puntata = Integer.valueOf(request.getParameter("puntata"));
		int nuovoSaldo = Integer.valueOf(request.getParameter("saldo"));
		if (utente.getUsername().equalsIgnoreCase("guest")) {
			utente.setSaldo(nuovoSaldo);
			request.getSession().setAttribute("user", utente);
			return;
    	}
		Giocata giocata = new Giocata();
		giocata.setUtente(utente);
		giocata.setImporto(puntata);
		int profit = nuovoSaldo - utente.getSaldo();
		giocata.setProfit(profit);
		us.updateSaldo(utente.getId(), Integer.valueOf(request.getParameter("saldo")));
		gs.add(giocata);
		request.getSession().setAttribute("user", us.getById(utente.getId()).get());
		
		System.out.println("Aggiunta giocata: "+ giocata.getId()+", "+giocata.getImporto()+", "
		+ giocata.getProfit()+ ", "+giocata.getData());
		
		/*try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "play";
	}*/
	
	@PostMapping
	public void addG(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		System.out.println("ci sono");
		Utente utente = (Utente) request.getSession().getAttribute("user");
		int puntata = (int) payload.get("puntata");
		System.out.println(puntata);
		int profit = (int) payload.get("ricavo");
		System.out.println(profit);
		int nuovoSaldo = utente.getSaldo() + profit;
		if (utente.getUsername().equalsIgnoreCase("guest")) {
			utente.setSaldo(nuovoSaldo);
			request.getSession().setAttribute("user", utente);
			return;
    	}
		Giocata giocata = new Giocata();
		giocata.setUtente(utente);
		giocata.setImporto(puntata);
		giocata.setProfit(profit);
		giocata.setSaldo(nuovoSaldo);
		us.updateSaldo(utente.getId(), nuovoSaldo);
		gs.add(giocata);
		request.getSession().setAttribute("user", us.getById(utente.getId()).get());
		
		System.out.println("Aggiunta giocata: "+ giocata.getId()+", "+giocata.getImporto()+", "
		+ giocata.getProfit()+ ", "+giocata.getData());
	}
}
