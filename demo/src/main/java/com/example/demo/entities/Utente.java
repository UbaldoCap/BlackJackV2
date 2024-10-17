package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer saldo;
    @Column(columnDefinition = "BIT")
    private Boolean online;
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Giocata> giocata;
    private String ip;

    public Utente(Integer id, String username, String password) {
        this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
