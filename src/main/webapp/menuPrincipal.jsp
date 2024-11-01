<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuario = (session != null) ? (String) session.getAttribute("usuario") : null;

    // Si no hay un usuario autenticado, redirigir al formulario de inicio de sesión
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
    <title>Menú Principal</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Menú Principal</h1>
    <p class="text-center">Bienvenido, <strong><%= usuario %></strong>!</p>
    <div class="row">
        <div class="col-md-4">
            <a href="perfilUsuario.jsp" class="btn btn-primary btn-block">Perfil</a>
        </div>
        <div class="col-md-4">
            <a href="configuracion.jsp" class="btn btn-secondary btn-block">Configuración</a>
        </div>
        <div class="col-md-4">
            <a href="cerrarSesion" class="btn btn-danger btn-block">Cerrar Sesión</a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
