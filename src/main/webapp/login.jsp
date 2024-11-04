<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar {
            background-color: #17a2b8;
        }
        .navbar-brand, .navbar-nav .nav-link {
            color: #fff !important;
        }
        .btn-custom {
            background-color: #17a2b8;
            color: white;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Horóscopo Chino</a>
    <div class="navbar-nav ml-auto">
        <span class="nav-link">Tu Horóscopo Chino</span>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center">Iniciar Sesión</h1>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= error %>
            </div>
            <% } %>
            <form action="IniciaSesion" method="post">
                <div class="form-group">
                    <label for="username">Nombre de Usuario:</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nombre de usuario" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contrasenia" required>
                </div>
                <button type="submit" class="btn btn-custom btn-block">Iniciar Sesión</button>
                <div class="text-center mt-3">
                    <p>¿No tienes una cuenta?</p>
                    <a href="registro.jsp" class="btn btn-secondary btn-block">Registrarse</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
