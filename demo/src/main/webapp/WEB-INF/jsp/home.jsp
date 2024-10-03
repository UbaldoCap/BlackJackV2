<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entities.Utente" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Home</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylehome.css">
</head>
<body>
	<%
        Utente utente = (Utente) session.getAttribute("user");
		List<Utente> lead = (List<Utente>) request.getAttribute("lead");
				%>
    <img id="logo" src = "/img/prova.png">  
    <!-- Contenuto principale -->
    <div class="container text-center mt-5">
        <h1 class="welcome">Benvenuto <%=utente.getUsername() %></h1>

        <div class="row justify-content-center">
            <!-- Bottone Gioca -->
            <div class="col-12 mb-3">
                <a href="/start" class="btn btn-primary btn-lg w-50">Gioca</a>
            </div>
            <!-- Bottone Logout -->
            	<div class="col-12 mb-3">
                <a href="/utente/logout" class="btn btn-danger btn-lg w-50">Logout</a>
            	</div>
        </div>

        <!-- Sezione Classifica -->
        <div class="position-fixed bottom-0 start-0 m-3" style="width: 300px;">
            <div class="card">
                <div class="card-header">
                    Classifica
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Username</th>
                                <th scope="col">Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Utente u : lead) {
                            	%>
                            	<tr>
                                	<td><%=u.getUsername() %></td>
                                	<td><%=u.getSaldo() %></td>
                                </tr> <%
                            }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
