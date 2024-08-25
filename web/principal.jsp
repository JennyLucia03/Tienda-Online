<%-- 
    Document   : principal
    Created on : 16/05/2023, 10:19:21 a. m.
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            body {
                background-color: #f4f4f4;
                font-family: Arial, sans-serif;
            }

            .container {
                width: 600px;
                margin: 10px auto 0;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                display: flex;
            }

            .text {
                flex-grow: 1;
                padding-left: 20px;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            .text p {
                font-size: 16px;
                line-height: 1.5;
                color: #555;
            }

            .image {
                text-align: center;
                margin-right: 20px;
            }

            img {
                max-width: 100%;
                height: auto;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .social-logos img {
                width: 30px;
                height: 30px;
                margin: 0 5px;
            }

            .button {
                text-align: center;
                margin-top: 20px;
            }

            .social-logos {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .button a {
                display: inline-block;
                padding: 10px 20px;
                background-color: #555;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .button a:hover {
                background-color: #333;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="img/boom.jpg" alt="55" width="55"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="srvUsuario?accion=home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">¿Quienes somos?</a>
                        </li>
                    </ul>
                    <div style="color:#FFFFFF; margin: 12px" class="pull-right">
                        <a href="login.jsp" class="btn btn-outline-success">Iniciar Sesion</a>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="text">
                <h2>¿Quienes somos?</h2>
                <div class="text">
                    <div class="image">
                        <img src="img/boom.jpg" alt="100" width="100">
                    </div>
                    <p>¡Bienvenido(a)!</p>
                    <p>"Tienda Geek y Cultura pop encuentra gran variedad de productos coleccionables 
                        relacionados con peliculas, series de tv, anime, Super heroes y mas. Figuras de accion, 
                        Juguetes exclusivos, Accesorios tematicos (Billeteras, collares, llaveros) Peluches y Cojines, 
                        Camisetas , gorros y mucho mas. Marcas: Funko, Neca, Mcfarlane, Hasbro, Mattel, DST, 
                        Bandai entre otras"</p>
                    <p>Nos puedes encontrar en nuestras redes sociales.</p>
                    <div class="social-logos">
                        <img src="img/facebook.png" alt="45" width="45">
                        <br>
                        <img src="img/instagram.png" alt="45" width="45">
                        <img src="img/whatsapp.png" alt="45" width="45">
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>

