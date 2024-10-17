package com.example.demo.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Utente;
import com.example.demo.services.UtenteService;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class MySessionListener implements HttpSessionListener {
	
    private static final Logger logger = LoggerFactory.getLogger(MySessionListener.class);
    
    @Autowired
	private UtenteService utenteService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Session created: " + se.getSession().getId());
        System.out.println("start");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session destroyed: " + se.getSession().getId());
        Utente u = (Utente) se.getSession().getAttribute("user");
        if (u != null) {
        	if (u.getUsername().equalsIgnoreCase("guest")) {
        		se.getSession().setAttribute("user", null);
                System.out.println("end");
                return;
        	}
        	u.setOnline(false);
        	u.setIp(null);
    		utenteService.add(u);
        }
		se.getSession().setAttribute("user", null);
        System.out.println("end");
    }
    
    
}