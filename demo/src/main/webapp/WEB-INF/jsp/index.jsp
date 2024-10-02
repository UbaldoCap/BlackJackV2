<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleindex.css">
</head>
<body>
    <img id="logo" src = "/img/prova.png"> 
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card mt-5">
                    <div class="card-header text-center">
                        <h3>Login</h3>
                    </div>
                    <div class="card-body">
                        <form action = "/utente/login" method = "get">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name = "username" placeholder="Inserisci il tuo username">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name = "password" placeholder="Inserisci la tua password">
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Login</button>
                        </form>
                        <br>
                        <a href="/utente/guest"><button class="btn btn-primary w-100">Gioca come Guest</button></a>
                    </div>
                    <div class="card-footer text-center">
                        <h5>Non hai un account? <a class = "register" href="/register">Registrati</a></h5>
                        <%String trovato = (String) request.getAttribute("trovato");
							if (trovato != null) {
								%><h1 id = "errore">Credenziali errate!</h1><%
							}
							%>
                    </div>
                </div>
            </div>
        </div>        
    </div>
</body>
</html>