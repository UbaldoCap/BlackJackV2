 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.entities.Utente" %>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Black Jack</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleplay.css?v=1" />
  </head>

  <body class="text-center">
    
    <%Utente utente = (Utente) session.getAttribute("user"); %>
    
    <audio id="clickConfermaPuntata" src="sounds/clickConfermaPuntata.wav"></audio>
    <audio id="clickPuntata" src="sounds/clickPuntata.wav"></audio>
    <audio id="mouseoverPuntate" src="sounds/mouseoverPuntate.wav"></audio>
    <audio id="flipcard" src="sounds/flipcard.wav"></audio>
    <audio id="victory" src="sounds/victory.wav"></audio>
    <audio id="defeat" src="sounds/defeat.wav"></audio>
    <audio id="draw" src="sounds/draw.wav"></audio>

    <div class="containerMain">

      <p id="containerSaldo">Saldo: <span id="saldo"><%=utente.getSaldo()%></span></p>

      <div id="containerPuntata" class="container text-center">
        <p id="failatuapuntata">Fai la tua puntata!</p>
        <button id="button5" class="buttonsPuntate">5</button>
        <button id="button10" class="buttonsPuntate">10</button>
        <button id="button20" class="buttonsPuntate">20</button>
        <button id="button50" class="buttonsPuntate">50</button>
        <button id="button100" class="buttonsPuntate">100</button>
        <button id="button500" class="buttonsPuntate">500</button>
        <button id="button1000" class="buttonsPuntate">1000</button>
        <p style="font-weight: bold">Totale puntata: <span id="totalePuntata">0</span></p>
        <button id="buttonConferma">Conferma puntata</button>
        <button id="ripristinaPuntata">Ripristina</button>
        <p id="errorePuntata" class="errore"></p>
      </div>
  
    <%if (utente.getSaldo()==0) {%>
    <button id="ricarica">Ricarica</button>	<%}%>
  
    <div id="containerPartita">
      <div id="containerDealer">
        <p><span class="highlight">Punteggio del Banco: <span id="punteggioDealer"></span></span></p>
        <div id="containerCarteDealer"></div>
        <p id="intestazioneAssicurazione"><span>Puntata assicurazione: <span id="puntataAssicurazione"></span></span></p>
      </div>

      <div id="containerAssicurazione">
        <div id="alertAssicurazione">
          <span class="testiSpan">Vuoi usare l'assicurazione?</span>
          <br>
          <button id="sì">SI</button>
          <button id="no">NO</button>
        </div>          
      </div>
  
      <div id="containerGiocatore" class="container">
        <div id="containerMani" class="row">
          <div class="containerMano col" data-mano="1">
            <p class="intestazioneMano">
              <span class="highlight">Punteggio (<%=utente.getUsername()%>): <span class="punteggio"></span></span>                
            </p>

            <div class="containerCarteGiocatore"></div>
            <p class="puntataMano"></p>
            <div class="containerAzioni container">
              <button class="hit azioniPartita">CARTA</button>
              <button class="stay azioniPartita">STAI</button>
              <button class="split azioniPartita">SPLIT</button>
              <button class="raddoppia azioniPartita">RADDOPPIA</button>
            </div>
            <p class="esito"></p>
          </div>
        </div>
      </div>

      <p id="profitto"></p>

      <button id="rigioca">RIGIOCA</button>
    </div>
  </div>

    

    
    
 <%if (utente.getUsername().equalsIgnoreCase("guest")) { %><a
      href="/utente/guest"
      ><button
        id="back-button"
        style="position: fixed; bottom: 20px; right: 20px"
      >
        INDIETRO
      </button></a
    >
    <a href="/register"
      ><button
        id="back-button"
        style="position: fixed; bottom: 20px; right: 150px"
      >
        REGISTRATI
      </button></a
    >
    <% } else { %><a href="/utente/login"
      ><button
        id="back-button"
        style="position: fixed; bottom: 20px; right: 20px"
      >
        INDIETRO
      </button></a
    ><% }%>   


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>   
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
  </body>
</html>
