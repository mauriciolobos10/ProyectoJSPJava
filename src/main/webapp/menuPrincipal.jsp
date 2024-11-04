<%@ page import="com.eductecno.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Obtener el objeto Usuario desde la sesión
    Usuario usuarioObj = (Usuario) session.getAttribute("usuario");

    // Verificar que el objeto Usuario no sea null y obtener el username
    String usuario  = (usuarioObj != null) ? usuarioObj.getUserName() : null;
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
    <title>Horóscopo Chino - Menú Principal</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar {
            background-color: #17a2b8;
        }
        .navbar-brand, .navbar-nav .nav-link {
            color: #fff !important;
        }
        .container {
            margin-top: 50px;
            text-align: center;
        }
        .btn-custom {
            background-color: #17a2b8;
            color: white;
            width: 180px;
            margin: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Horóscopo Chino</a>
    <div class="navbar-nav ml-auto">
        <span class="nav-link">Tu Horóscopo Chino</span>
        <a class="nav-link" href="cerrarSesion">Logout</a>
    </div>
</nav>

<div class="container">
    <h2 class="mt-5">¿Qué deseas hacer, <strong><%= usuario %></strong>?</h2>
    <div class="d-flex justify-content-center mt-4">
        <a href="ConsultarHoroscopoServlet" class="btn btn-custom">Conoce tu animal</a>
        <a href="buscarUsuarios.jsp" class="btn btn-custom">Buscar usuarios</a>
        <a href="modificarDatos.jsp" class="btn btn-custom">Modificar datos</a>
        <a href="eliminarCuenta.jsp" class="btn btn-custom">Eliminar cuenta</a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
