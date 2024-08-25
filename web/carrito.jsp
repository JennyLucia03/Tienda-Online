<%-- 
    Document   : carrito
    Created on : 14/04/2023, 2:36:31 p. m.
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
                            <a class="nav-link active" aria-current="page" href="srvUsuario?accion=home2">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=home2">Seguir comprando</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=misCompras">Mis Compras</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="srvUsuario?accion=editarC">Actualizar Usuario</a>
                        </li>
                    </ul>
                    <p style="color:#FFFFFF; margin: 12px">                    
                        Bienvenido - ${cliente.nombreUsuario}
                        <small>Usted es,${cliente.cargo.nombreCargo} </small>
                    </p>

                    <div style="color:#FFFFFF; margin: 12px" class="pull-right">
                        <a href="srvUsuario?accion=cerrar" class="btn btn-outline-success">Cerrar Sesion</a>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h3>Carrito</h3>
            <br>
            <div class="row">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>Generar Compra</h3>
                        </div>
                        <div class="card-body">
                            <label>Subtotal</label>
                            <input type="text" value="$.${totalPagar}0" readonly="" class="form-control">
                            <label>Descuento</label>
                            <input type="text" value="$.0.00" readonly="" class="form-control">
                            <label>Precio envío</label>
                            <input type="text" value="$.${precioEnvio}0" readonly="" class="form-control">
                            <label>Total Pagar: </label>
                            <input type="text" value="$.${Total}0" readonly="" class="form-control">
                        </div>
                        <div class="card-footer">
                            <a class="btn btn-info btn-block" data-bs-toggle="modal" data-bs-target="#exampleModal" id="enlace">Pago Tarjeta</a>
                            <a class="btn btn-danger btn-block" data-bs-toggle="modal" data-bs-target="#exampleModa2" id="mi-enlace">Pago PSE</a>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Envío y Pago</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <!-- CREDIT CARD FORM STARTS HERE -->
                                    <div class="panel panel-default credit-card-box customwidth center-block">
                                        <div class="panel-body">
                                            <form  class="needs-validation" action="srvUsuario?accion=RealizarPago" method="POST" novalidate>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="direccion">Dirección envío</label>
                                                            <div>
                                                                <input 
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="direccion"
                                                                    placeholder="CL 87 A sur #7-06"
                                                                    required>
                                                                <div class="valid-feedback">
                                                                    ¡Dirección válida!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Dirección inválida
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="panel-heading display-table" >
                                                    <div class="row display-tr" >
                                                        <div class="display-td" id="cardLogoTop">                            
                                                            <img class="img-responsive pull-right" src="https://i.imgur.com/gIMFDbp.png">
                                                            <!-- <img class="img-responsive pull-right" src="https://i.imgur.com/WluzPvZ.png">
                                                            <img class="img-responsive pull-right" src="https://i.imgur.com/H5lJRwk.png">
                                                            <img class="img-responsive pull-right" src="https://i.imgur.com/1U8OBnM.png">
                                                            <img class="img-responsive pull-right" src="https://i.imgur.com/iqIDYfz.png">
                                                            -->
                                                        </div>
                                                    </div>                    
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="cardNumber">Número de tarjeta</label>
                                                            <div>
                                                                <input 
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="cardNumber"
                                                                    placeholder="XXXX-XXXX-XXXX-XXXX"
                                                                    pattern="^[0-9]{16}"
                                                                    required>
                                                                <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>
                                                                <div class="valid-feedback">
                                                                    ¡Número de tarjeta válida!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Número de tarjeta inválida
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-7 col-md-7">
                                                        <div>
                                                            <label for="cardExpiry"><span class="hidden-xs">Expiración Fecha</span></label>
                                                            <input 
                                                                type="text" 
                                                                class="form-control" 
                                                                name="cardExpiry"
                                                                placeholder="MM / YY"
                                                                pattern="^[0-1]{1}[0-9]{1}/[2-3]{1}[3-9]{1}"
                                                                required>
                                                            <div class="valid-feedback">
                                                                ¡Fecha válida!
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Fecha inválida
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-5 col-md-5 pull-right">
                                                        <div>
                                                            <label for="cardCVC">CVC</label>
                                                            <input 
                                                                type="text" 
                                                                class="form-control"
                                                                name="cardCVC"
                                                                placeholder="CVC"
                                                                pattern="^[0-9]{3}"
                                                                required>
                                                            <div class="valid-feedback">
                                                                ¡Código CVC válido!
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Código CVC inválido
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <input type="submit" name="accion" class="btn btn-dark"
                                                               style="padding-left: 2.5rem; padding-right: 2.5rem;" value="Realizar Pago" id="mi-input" required>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>            
                                    <!-- CREDIT CARD FORM ENDS HERE -->
                                    <script>
                                        (function () {
                                            'use strict'

                                            // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                            var forms = document.querySelectorAll('.needs-validation')

                                            // Loop over them and prevent submission
                                            Array.prototype.slice.call(forms)
                                                    .forEach(function (form) {
                                                        form.addEventListener('submit', function (event) {
                                                            if (!form.checkValidity()) {
                                                                event.preventDefault()
                                                                event.stopPropagation()
                                                            }

                                                            form.classList.add('was-validated')
                                                        }, false)
                                                    })
                                        })()
                                    </script>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button onclick="location.reload()" type="button" class="btn btn-secondary" id="mi-boton" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                    <script>
                        // Desactivar el botón Atrás del navegador
                        history.pushState(null, null, location.href);
                        window.onpopstate = function () {
                            history.go(1);
                        };
                    </script>
                </div>      
                <div class="modal fade" id="exampleModa2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Envío y Pago</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <!-- CREDIT CARD FORM STARTS HERE -->
                                    <div class="panel panel-default credit-card-box customwidth center-block">
                                        <div class="panel-body">
                                            <form  class="needs-validation" action="srvUsuario?accion=RealizarPago2" method="POST" novalidate>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="direccion">Dirección envío</label>
                                                            <div>
                                                                <input 
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="direccion"
                                                                    placeholder="CL 87 A sur #7-06"
                                                                    required>
                                                                <div class="valid-feedback">
                                                                    ¡Dirección válida!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Dirección inválida
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="banco">Banco</label>
                                                            <div>
                                                                <input 
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="banco"
                                                                    placeholder="Banco "
                                                                    pattern="[Banco]+[a-z A-Z]+"
                                                                    required>
                                                                <div class="valid-feedback">
                                                                    ¡Banco válido!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Banco inválido
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="tipCuenta">Tipo de cuenta</label>
                                                            <div>
                                                                <select class=" text-center col-sm-20" name="tipCuenta" required>
                                                                    <option value="">Seleccione un tipo de cuenta</option>
                                                                    <option value="Ahorros">Cuenta de ahorros</option>
                                                                    <option value="Corriente">Cuenta corriente</option>
                                                                </select><br>
                                                                <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>
                                                                <div class="valid-feedback">
                                                                    ¡Tipo de cuenta válida!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Tipo de cuenta inválida
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label for="numCuenta">Número de Cuenta</label>
                                                            <div>
                                                                <input 
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="numCuenta"
                                                                    placeholder="XXXX-XXXX-XXXX-XXXX"
                                                                    pattern="^[0-9]{9}||^[0-9]{16}"
                                                                    required>
                                                                <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>
                                                                <div class="valid-feedback">
                                                                    ¡Número de cuenta válida!
                                                                </div>
                                                                <div class="invalid-feedback">
                                                                    Número de cuenta inválida
                                                                </div>
                                                            </div>
                                                        </div>                            
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <input type="submit" name="accion" class="btn btn-dark"
                                                               style="padding-left: 2.5rem; padding-right: 2.5rem;" value="Realizar Pago" required>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>            
                                    <!-- CREDIT CARD FORM ENDS HERE -->
                                    <script>
                                        (function () {
                                            'use strict'

                                            // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                            var forms = document.querySelectorAll('.needs-validation')

                                            // Loop over them and prevent submission
                                            Array.prototype.slice.call(forms)
                                                    .forEach(function (form) {
                                                        form.addEventListener('submit', function (event) {
                                                            if (!form.checkValidity()) {
                                                                event.preventDefault()
                                                                event.stopPropagation()
                                                            }

                                                            form.classList.add('was-validated')
                                                        }, false)
                                                    })
                                        })()
                                    </script>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button onclick="location.reload()" type="button" class="btn btn-secondary" id="mi-boton" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                    <script>
                        // Desactivar el botón Atrás del navegador
                        history.pushState(null, null, location.href);
                        window.onpopstate = function () {
                            history.go(1);
                        };
                    </script>
                </div>  
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ITEM</th>
                                <th>NOMBRES</th>
                                <th>DESCRIPCIÓN</th>
                                <th>PRECIO</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carrito}">
                                <tr>
                                    <td align="center">${car.getItem()}</td> 
                                    <td>${car.getNombres()}</td> 
                                    <td align="center">${car.getDescripcion()}
                                        <br>
                                        <img src="ControladorIMG?id=${car.getIdProducto()}" width="100" height="100">
                                    </td> 
                                    <td>$.${car.getPrecioCompra()}0</td> 
                                    <td>
                                        <div style="display:flex">
                                            <a href="srvUsuario?accion=Aumentar&idp=${car.getIdProducto()}" class="btn bg-dark" style="color:white">+</a>
                                            <input id="Cantidad" class="text-center" size="1" value="${car.getCantidad()}" disabled="True">
                                            <a href="srvUsuario?accion=Disminuir&idp=${car.getIdProducto()}" class="btn bg-dark" style="color:white">-</a>
                                        </div>
                                    <td>$.${car.getSubTotal()}0</td> 
                                    <td align="center">
                                        <input type="hidden" id="idp" value="${car.getIdProducto()}">
                                        <a href="srvUsuario?accion=Delete&idp=${car.getIdProducto()}" id="btnDelete"><img src="img/eliminar.png" width="30" height="30"/></a>
                                    </td> 
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
