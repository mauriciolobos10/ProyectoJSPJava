<%@ page import="com.eductecno.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Usuario</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="menuPrincipal.jsp">Horóscopo Chino</a>
    <span class="nav-link">Tu Horóscopo Chino</span>
    <div class="navbar-nav ml-auto">
        <a class="nav-link" href="cerrarSesion">Logout</a>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center">Modificar Perfil</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="ModificarUsuarioServlet" method="post">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= usuario.getNombre() %>"
                           required>
                </div>
                <div class="form-group">
                    <label for="email">Correo electrónico:</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= usuario.getEmail() %>"
                           required>
                </div>
                <div class="form-group">
                    <label for="username">Nombre de usuario:</label>
                    <input type="text" class="form-control" id="username" name="username"
                           value="<%= usuario.getUserName() %>" required>
                </div>
                <div class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"
                           value="<%= usuario.getFechaNacimiento() %>" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Ingrese nueva contraseña (opcional)">
                </div>
                <div class="form-group">
                    <label for="repetirPassword">Repita la contraseña:</label>
                    <input type="password" class="form-control" id="repetirPassword" name="repetirPassword"
                           placeholder="Repita nueva contraseña (opcional)">
                </div>
                <button type="submit" class="btn btn-custom btn-block">Guardar Cambios</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
