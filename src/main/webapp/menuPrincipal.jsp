<%@ page import="com.eductecno.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) {
        session.removeAttribute("mensaje");
    }
    Usuario usuarioObj = (Usuario) session.getAttribute("usuario");

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
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/styles.css">
    <style>
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
    <a class="navbar-brand" href="menuPrincipal.jsp">Horóscopo Chino</a>
    <span class="nav-link">Tu Horóscopo Chino</span>
    <div class="navbar-nav ml-auto">
        <a class="nav-link" href="cerrarSesion">Logout</a>
    </div>
</nav>

<div class="container">
    <h2 class="mt-5">¿Qué deseas hacer, <strong><%= usuario %></strong>?</h2>
    <div class="d-flex justify-content-center mt-4">
        <a href="ConsultarHoroscopoServlet" class="btn btn-custom">Conoce tu animal</a>
        <a href="buscarUsuario.jsp" class="btn btn-custom">Buscar usuarios</a>
        <a href="modificarUsuario.jsp" class="btn btn-custom">Modificar datos</a>

        <form action="EliminarUsuarioServlet" method="post">
            <button type="submit" class="btn btn-custom">Eliminar Cuenta</button>
        </form>
    </div>
</div>

<div class="modal fade" id="mensajeModal" tabindex="-1" role="dialog" aria-labelledby="mensajeModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="mensajeModalLabel">Mensaje</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="mensajeContenido">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        var mensaje = "<%= mensaje != null ? mensaje : "" %>";

        if (mensaje) {
            $('#mensajeContenido').text(mensaje);
            $('#mensajeModal').modal('show');
        }
    });
</script>

</body>
</html>
