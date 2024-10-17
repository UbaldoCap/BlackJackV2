 

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
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleplayNuovo.css?v=1" />
  </head>

  <body>
    
        <%Utente utente = (Utente) session.getAttribute("user"); %>
    

    <audio id="clickConfermaPuntata" src="${pageContext.request.contextPath}/sounds/clickConfermaPuntata.wav"
    ></audio>
    <audio id="clickPuntata" src="${pageContext.request.contextPath}/sounds/clickPuntata.wav"></audio>
    <audio id="mouseoverPuntate" src="${pageContext.request.contextPath}/sounds/mouseoverPuntate.wav"></audio>

    <div id="containerPuntata" class="container">
      <p>Fai la tua puntata!</p>
      <button id="button5" class="buttonsPuntate">5</button>
      <button id="button10" class="buttonsPuntate">10</button>
      <button id="button20" class="buttonsPuntate">20</button>
      <button id="button50" class="buttonsPuntate">50</button>
      <button id="button100" class="buttonsPuntate">100</button>
      <button id="button500" class="buttonsPuntate">500</button>
      <button id="button1000" class="buttonsPuntate">1000</button>
      <p>Totale puntata: <span id="totalePuntata">0</span></p>
      <button id="buttonConferma">Conferma puntata</button>
      <button id="ripristinaPuntata">Ripristina</button>
      <p id="errorePuntata" class="errore"></p>
    </div>

	<%if (utente.getSaldo()==0) {%>
	<button id="ricarica">Ricarica</button>	<%}%>
    <div id="containerPartita" class="container">
      <div id="containerDealer" class="container">
        <h2>Punteggio del Banco: <span id="punteggioDealer"></span></h2>
        <div id="containerCarteDealer" class="container"></div>
      </div>

      <div id="containerAssicurazione" class="container">
        <div id="alertAssicurazione" class="container">
          <p >Vuoi usare l'assicurazione?</p>
          <button id="sì">SI</button>
          <button id="no">NO</button>
        </div>
        <h4 id="intestazioneAssicurazione">Puntata assicurazione: <span id="puntataAssicurazione"></span></h4>
      </div>

      <div id="containerGiocatore" class="container">
        <div id="containerMani" class="container">
          <div class="containerMano container" data-mano="1">
            <h2 class="intestazioneMano">
              Punteggio (<%=utente.getUsername()%>): <span class="punteggio"></span>
            </h2>

            <div class="containerCarteGiocatore container"></div>
            <h4 class="puntataMano"></h4>
            <div class="containerAzioni container">
              <button class="hit azioniPartita">CARTA</button>
              <button class="stay azioniPartita">STAI</button>
              <button class="split azioniPartita">SPLIT</button>
              <button class="raddoppia azioniPartita">RADDOPPIA</button>
            </div>
            <h5 class="esito"></h5>
          </div>
        </div>        
    </div>

    <!--
        <div id="custom-alert" class="alert hidden">
          <span id="alert-message"></span>
          <button id="close-alert">CHIUDI</button>
        </div>
      <button id="rigioca">RIGIOCA</button>
        -->

    <h4 id="profitto"></h4>
  </div>
  
    <p>Saldo: <span id="saldo"><%=utente.getSaldo()%></span></p>
    <button id="rigioca">RIGIOCA</button>

    
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
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
  </body>
</html>
