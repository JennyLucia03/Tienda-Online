
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("usuario") != null) {
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
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form class="needs-validation" action="srvUsuario?accion=registrarAD" method="POST" novalidate>
                            <br>
                            <h2>Registro</h2>
                            <br>
                            <!-- Nombre del usuario -->
                            <div class="form-outline mb-2">
                                <label class="form-label">Usuario</label>
                                <input type="text" class="form-control form-control-lg" name="txtusu" required>
                                <div class="valid-feedback">
                                    ¡Se ve bien!
                                </div>
                                <div class="invalid-feedback">
                                    Por favor escriba el usuario
                                </div>
                            </div>
                            <!-- Contraseña -->
                            <div class="form-outline mb-2">
                                <label class="form-label">Contraseña</label>
                                <input type="password" class="form-control form-control-lg" name="txtcon" required>
                                <div class="valid-feedback">
                                    ¡Se ve bien!
                                </div>
                                <div class="invalid-feedback">
                                    Por favor escriba la contraseña
                                </div>
                            </div>
                            <!-- Cargo -->
                            <div class="form-outline mb-2">
                                <label for="tipCargo">Tipo de cuenta</label>
                                <div>
                                    <select class=" text-center col-sm-40" name="tipCargo" required>
                                        <option value="">Seleccione un tipo de cargo</option>
                                        <option value="ADMINISTRADOR">Administrador</option>
                                        <option value="DISTRIBUIDOR">Distribuidor</option>
                                    </select><br>
                                    <span class="input-group-addon"><i class="fa fa-credit-card" id="cardlogo" style="color:purple;font-size:2rem;"></i></span>
                                    <div class="valid-feedback">
                                        ¡Tipo de cargo válido!
                                    </div>
                                    <div class="invalid-feedback">
                                        Tipo de cargo inválido
                                    </div>
                                </div>
                            </div>   
                            <!-- Correo -->
                            <div class="form-outline mb-2">
                                <label class="form-label">Correo</label>
                                <input type="email" class="form-control form-control-lg" name="txtcor" required>
                                <div class="valid-feedback">
                                    ¡Se ve bien!
                                </div>
                                <div class="invalid-feedback">
                                    Por favor escriba el correo
                                </div>
                            </div>
                            <!-- Direccion -->
                            <div class="form-outline mb-3">
                                <label class="form-label">Dirección</label>
                                <input type="text" class="form-control form-control-lg" name="txtdir" required>
                                <div class="valid-feedback">
                                    ¡Se ve bien!
                                </div>
                                <div class="invalid-feedback">
                                    Por favor escriba la dirección
                                </div>
                            </div>
                            <input type="submit" name="accion" class="btn btn-dark"
                                   style="padding-left: 2.5rem; padding-right: 2.5rem;" value="Registrarse">
                        </form>
                        <script>
                            // Example starter JavaScript for disabling form submissions if there are invalid fields
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
        </section>
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
