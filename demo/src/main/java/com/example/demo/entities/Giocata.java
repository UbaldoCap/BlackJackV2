package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Giocata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente utente;

    private Integer importo;
    private Integer profit;
    private Integer saldo;

	private LocalDateTime data;

    public Giocata(Integer id, Utente utente, Integer importo, Integer profit) {
        this.id = id;
        this.utente = utente;
        this.importo = importo;
        this.profit = profit;
        data = LocalDateTime.now();
    }

    public Giocata() {
        data = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Integer getImporto() {
        return importo;
    }

    public void setImporto(Integer importo) {
        this.importo = importo;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

}
