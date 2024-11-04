
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String animal = (String) session.getAttribute("animal");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horóscopo Chino - Conoce tu animal</title>
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
    <h2 class="mt-5">Conoce a tu animal del horóscopo chino</h2>
    <h2 class="mt-5">Tu animal es <strong><%= animal %></strong></h2>
</div>
</body>
</html>
