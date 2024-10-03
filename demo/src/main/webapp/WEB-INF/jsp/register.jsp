<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina di Registrazione</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleregister1.css">
</head>
<body>
	<%String err = (String) request.getAttribute("err");%>
    <img id="logo" src = "/img/prova.png"> 
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card mt-5">
                    <div class="card-header text-center">
                        <h3>Registrati</h3>
                    </div>
                    <div class="card-body">
                        <form action="/utente/add" method ="post">
                            <div class="mb-3">
                                <label for="name" class="form-label">Nome Completo</label>
                                <input type="text" class="form-control" id="name" name="nome" placeholder="Inserisci il tuo nome" required>
                            </div>
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Inserisci il tuo username" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Crea una password" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Registrati</button>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <h5>Hai già un account? <a class="accedi" href="/">Accedi</a></h5>
                        <%if (request.getAttribute("err") != null) {
                                	%><small id="err">Username gia in uso</small><% 
                                }%>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
