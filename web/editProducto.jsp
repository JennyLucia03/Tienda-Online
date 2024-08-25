<%-- 
    Document   : editProducto
    Created on : 3/05/2023, 3:43:29 p. m.
    Author     : User
--%>

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
        <div class="container mt-3" style="display:flex; justify-content:center; align-items:center;">
            <div class="card col-sm-4">
                <div class="card-header d-flex">
                    <h4>Actualizar Producto</h4>
                </div>   
                <div class="row">
                    <div class="col-sm-20">
                        <div class="container">
                            <!-- CREDIT CARD FORM STARTS HERE -->
                            <div class="panel panel-default credit-card-box customwidth center-block">
                                <div class="panel-body">
                                    <form  class="needs-validation" action="srvUsuario?accion=actualizarP" method="POST" enctype="multipart/form-data" novalidate>
                                        <input type="text" name="txtid" value="${producto.id}" style="display: none;">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label for="nomp">Nombre</label>
                                                    <div>
                                                        <input 
                                                            type="text"
                                                            class="form-control"
                                                            name="nomp"
                                                            value="${producto.nombres}"
                                                            pattern="[a-z A-Z]+">
                                                    </div>
                                                </div>                            
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group custom-file">
                                                    <input type="file" class="custom-file-input" value="${producto.foto}" name="inputfoto" accept="image/*">
                                                </div>                            
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label for="descripcion">Descripción</label>
                                                    <div>
                                                        <textarea class="form-control" type="text" name="descripcion" rows="3" required>${producto.descripcion}</textarea>
                                                    </div>
                                                </div>                            
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label for="precio">Precio</label>
                                                    <div>
                                                        <input 
                                                            type="text"
                                                            class="form-control"
                                                            name="precio"
                                                            value="${producto.precio}"
                                                            pattern="[0-9]+">
                                                        <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>
                                                    </div>
                                                </div>                            
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label for="stock">Stock</label>
                                                    <div>
                                                        <input 
                                                            type="text"
                                                            class="form-control"
                                                            name="stock"
                                                            value="${producto.stock}"
                                                            pattern="[0-9]+">
                                                        <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>

                                                    </div>
                                                </div>                            
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <input type="submit" name="accion" class="btn btn-dark"
                                                       style="padding-left: 2.5rem; padding-right: 2.5rem;" value="Actualizar Producto" required>
                                            </div>
                                        </div>
                                        <br>
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
                </div>
            </div>
        </div>  
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
