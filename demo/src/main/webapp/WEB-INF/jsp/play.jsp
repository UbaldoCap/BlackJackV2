<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.entities.Utente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Black Jack</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stlyeplay.css">
        <script src="${pageContext.request.contextPath}/js/script1.js"></script>
    </head>

    <body>
    <%
        Utente utente = (Utente) session.getAttribute("user");
				%>
        <img id="logo" src="./img/prova.png" alt="Logo">
        <h2>Dealer: <span id="dealer-sum"></span></h2>
        <div id="dealer-cards">
            <img id="hidden" src="./cards/retro.png">
        </div>

        <h2>You: <span id="your-sum"></span></h2>
        <div id="your-cards"></div>

        <br>
        <button id="hit">Hit</button>
        <button id="stay">Stay</button>
        <button id="rigioca" class="nascosta">RIGIOCA</button>
        <p id="results"></p>
        <div id="custom-alert" class="alert hidden">
        	<span id="alert-message"></span>
        	<button id="close-alert">CHIUDI</button>
    	</div>
        <%if (utente.getNome().equalsIgnoreCase("guest")) {
        	%><a href="/utente/guest"><button id="back-button" style="position: fixed; bottom: 20px; right: 20px;">INDIETRO</button></a>
        	  <a href="/register"><button id="back-button" style="position: fixed; bottom: 20px; right: 150px;">REGISTRATI</button></a>
        	<%
        } else {
        	%><a href="/utente/login"><button id="back-button" style="position: fixed; bottom: 20px; right: 20px;">INDIETRO</button></a><%
        }%>
    </body>
</html>
