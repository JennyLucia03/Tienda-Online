
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("distribuidor") != null) {
%>
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
                            <a class="nav-link active" aria-current="page" href="srvUsuario?accion=pedido">Envíos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=editarD">Actualizar datos</a>
                        </li>
                    </ul>
                    <p style="color:#FFFFFF; margin: 12px">                    
                        Bienvenido - ${distribuidor.nombreUsuario}
                        <small>Usted es,${distribuidor.cargo.nombreCargo} </small>
                    </p>

                    <div style="color:#FFFFFF; margin: 12px" class="pull-right">
                        <a href="srvUsuario?accion=cerrar" class="btn btn-outline-success">Cerrar Sesion</a>
                    </div>

                </div>
            </div>
        </nav> 
        <div class="container mt-4">  
            <a class="btn btn-success btn-block" href="srvUsuario?accion=pedido">  Volver  </a>
            <br>
            <br>
            <div class="card"> 
                <div class="card-header d-flex">
                    <h2>Detalle</h2>
                </div>   
                <div class="card-body">
                    <table class="table tab-pane">
                        <thead class="thead-light">
                            <tr class="text-center">
                                <th>Código compra</th>                               
                                <th>Articulo</th>
                                <th>Cantidad</th>
                                <th>Precio Compra</th>                                                   
                                <th></th>                                                   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${myDetalle}">
                                <tr class="text-center">
                                    <td>C00${p.getIdcompra()}</td> 
                                    <td>                                        
                                        <label><i>${p.producto.nombres}</i></label><br>
                                    </td>                                                                
                                    <td>${p.getCantidad()}</td>
                                    <td>$.${p.getPrecioCompra()}0</td> 
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                </div>
                <div class="card-footer d-flex">
                    <label class="col-sm-9 text-right mt-1">PRECIO ENVÍO</label>
                    <input type="text" class=" text-center col-sm-3" value="$.10000.00" disabled="true">
                </div>
                <div class="card-footer d-flex">
                    <label class="col-sm-9 text-right mt-1">TOTAL</label>
                    <c:set var="p" value="${myDetalle[0]}"/>
                    <input type="text" class=" text-center col-sm-3 my-input" id="myInput" name="myInput" value="$.${p.compra.monto}0" disabled="true"/>
                </div>
            </div>          
        </div> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("identificar.jsp");
    }
%>