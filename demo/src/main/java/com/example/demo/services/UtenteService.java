package com.example.demo.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Utente;
import com.example.demo.repositories.UtenteRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepo utenteRepo;

    public Utente add(Utente utente) {
        return utenteRepo.save(utente);
    }

    public List<Utente> getAll() {
        return utenteRepo.findAll();
    }

    public Optional<Utente> getById(Integer id) {
        return utenteRepo.findById(id);
    }

    public Optional<Utente> update(Integer id, Boolean b) {
        Optional<Utente> optionalUtente = utenteRepo.findById(id);
        if (optionalUtente.isPresent()) {
            optionalUtente.get().setOnline(b);
            return Optional.of(utenteRepo.save(optionalUtente.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Utente> delete(Integer id) {
        Optional<Utente> optionalUtente = utenteRepo.findById(id);
        if (optionalUtente.isPresent()) {
            utenteRepo.deleteById(id);
            return optionalUtente;
        } else {
            return Optional.empty();
        }
    }
    
    public Utente getByUsernamePassword(String username, String password) {
        return utenteRepo.findByUsernameAndPassword(username, password);
    }
    
    public List<Utente> leaderBoard() {
    	return utenteRepo.findTop5ByOrderBySaldoDesc();
    }
    
    public Boolean online(Integer id) {
    	return utenteRepo.findOnlineStatusById(id);
    }
}
