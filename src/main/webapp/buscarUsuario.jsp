<%@ page import="com.eductecno.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Búsqueda Usuario</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="menuPrincipal.jsp">Horóscopo Chino</a>
    <span class="nav-link">Tu Horóscopo Chino</span>
</nav>

<div class="container mt-5">
    <h2 class="text-center">Busca un usuario por nombre</h2>
    <form action="BuscarUsuario" method="post" class="mb-4">
        <div class="form-group">
            <label for="nombre">Nombre de Usuario:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" required>
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>


    <%
        Usuario usuarioEncontrado = (Usuario) request.getAttribute("usuarioEncontrado");
        String mensaje = (String) request.getAttribute("mensaje");
    %>
    <% if (usuarioEncontrado != null) { %>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Detalles del Usuario</h5>
            <p><strong>Nombre:</strong> <%= usuarioEncontrado.getNombre() %></p>
            <p><strong>Username:</strong> <%= usuarioEncontrado.getUserName() %></p>
            <p><strong>Fecha de Nacimiento:</strong> <%= usuarioEncontrado.getFechaNacimiento() %></p>
        </div>
    </div>
    <% } else if (mensaje != null) { %>
    <div class="alert alert-warning" role="alert">
        <%= mensaje %>
    </div>
    <% } %>

</div>

</body>
</html>
