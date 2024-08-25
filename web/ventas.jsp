<%-- 
    Document   : ventas
    Created on : 5/05/2023, 10:33:00 a. m.
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
                            <a class="nav-link active" aria-current="page" href="srvUsuario?accion=home3">Mis productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=misVentas">Mis Ventas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=pago">Pagos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=editarA">Actualizar Usuario</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="registroAD.jsp">Registro</a>
                        </li>
                    </ul>
                    <p style="color:#FFFFFF; margin: 12px">                    
                        Bienvenido - ${usuario.nombreUsuario}
                        <small>Usted es,${usuario.cargo.nombreCargo} </small>
                    </p>

                    <div style="color:#FFFFFF; margin: 12px" class="pull-right">
                        <a href="srvUsuario?accion=cerrar" class="btn btn-outline-success">Cerrar Sesion</a>
                    </div>

                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h2>Mis Ventas</h2>
            <div class="row">            
                <table class="table tab-pane">
                    <thead class="thead-light">
                        <tr class="text-center">
                            <th>CODIGO DE VENTA</th> 
                            <th>Cliente</th>
                            <th>Fecha de Venta</th>
                            <th>Monto</th>                                                   
                            <th>Codigo de Pago</th>                                                   
                            <th>Estado</th>                                                   
                            <th></th>                                                   
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${myVentas}">
                            <tr class="text-center">
                                <td>C00${p.getId()}</td> 
                                <td>${p.usuario.nombreUsuario}</td>
                                <td>${p.getFecha()}</td>
                                <td>${p.getMonto()}</td>                                                                                                       
                                <td>P00${p.getIdpago()}</td>                                                                                                       
                                <td>${p.getEstado()}</td>                                                                                                       
                                <td>
                                    <a href="srvUsuario?accion=verDetalle2&idcompra=${p.getId()}">Ver Detalle</a>
                                </td>                                                                                                       
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>             
            </div>          
        </div> 
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>
