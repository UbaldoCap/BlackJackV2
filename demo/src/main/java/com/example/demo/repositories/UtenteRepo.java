package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Utente;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Integer> {
	Utente findByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT u FROM Utente u ORDER BY u.saldo DESC")
    List<Utente> findTop5ByOrderBySaldoDesc();
}
