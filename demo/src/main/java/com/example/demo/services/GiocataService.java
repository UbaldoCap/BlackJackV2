package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Giocata;
import com.example.demo.repositories.GiocataRepo;

@Service
public class GiocataService {
	
	@Autowired 
	private GiocataRepo gr;
	
	public Giocata add(Giocata giocata) {
		return gr.save(giocata);
	}
}
