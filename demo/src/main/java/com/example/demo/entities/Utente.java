package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer saldo;
    @Column(columnDefinition = "BIT")
    private Boolean online;
    @OneToMany(mappedBy = "utente")
    private List<Giocata> giocata;

    public Utente(Integer id, String nome, String username, String password) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.password = password;
        saldo = 1000;
        giocata = new ArrayList<>();
        online = false;
    }

    public Utente() {
        giocata = new ArrayList<>();
        saldo = 1000;
        online = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public List<Giocata> getGiocata() {
        return giocata;
    }

    public void setGiocata(List<Giocata> giocata) {
        this.giocata = giocata;
    }

    public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", username=" + username + ", password=" + password + ", saldo="
				+ saldo + ", online=" + online + ", giocata=" + giocata + "]";
	}
	
	
}
