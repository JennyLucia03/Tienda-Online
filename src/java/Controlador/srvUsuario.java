package Controlador;

import Modelo.Carrito;
import Modelo.Compra;
import Modelo.CompraDAO;
import Modelo.DAOUSUARIO;
import Modelo.DetalleCompra;
import Modelo.Fecha;
import Modelo.Pago;
import Modelo.PagoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.cargo;
import Modelo.usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

@MultipartConfig
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    List<Producto> productos = new ArrayList<>();
    List<Carrito> listaCarrito = new ArrayList<>();
    List<Compra> compras = new ArrayList<>();
    int item = 0;
    double totalPagar = 0.0;
    double precioEnvio = 10000;
    double Total = 0;
    int cantidad = 1;
    int idp;
    Carrito car;
    HttpSession sesion = null;
    DAOUSUARIO dao = new DAOUSUARIO();
    Pago pag = new Pago();
    usuario usuario;
    cargo carg;
    CompraDAO daoo = new CompraDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        productos = pdao.listar();
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        //verificar(request, response);
                        usuario = this.obtenerUsuario(request);
                        dao = new DAOUSUARIO();
                        usuario = dao.identificar(usuario);
                        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
                            sesion = request.getSession();
                            sesion.setAttribute("usuario", usuario);
                            request.setAttribute("msje", "Bienvenido al sistema");
                            this.getServletConfig().getServletContext().getRequestDispatcher("/administrador.jsp").forward(request, response);
                        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("CLIENTE")) {
                            sesion = request.getSession();
                            sesion.setAttribute("cliente", usuario);
                            this.getServletConfig().getServletContext().getRequestDispatcher("/identificar2.jsp").forward(request, response);
                        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("DISTRIBUIDOR")) {
                            sesion = request.getSession();
                            sesion.setAttribute("distribuidor", usuario);
                            this.getServletConfig().getServletContext().getRequestDispatcher("/distribuidor.jsp").forward(request, response);}
                        else {
                            request.setAttribute("msje", "Credenciales Incorrectas");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                        break;
                    case "cerrar":
                        cerrarsession(request, response);
                    case "Comprar":
                        totalPagar = 0.0;
                        int po = 0;
                        cantidad = 1;
                        idp = Integer.parseInt(request.getParameter("id"));
                        p = pdao.listarId(idp);
                        if (listaCarrito.size() > 0) {
                            for (int i = 0; i < listaCarrito.size(); i++) {
                                if (idp == listaCarrito.get(i).getIdProducto()) {
                                    po = i;
                                }
                            }
                            if (idp == listaCarrito.get(po).getIdProducto()) {
                                cantidad = listaCarrito.get(po).getCantidad() + cantidad;
                                double subtotal = listaCarrito.get(po).getPrecioCompra() * cantidad;
                                listaCarrito.get(po).setCantidad(cantidad);
                                listaCarrito.get(po).setSubTotal(subtotal);
                            } else {
                                item = item + 1;
                                car = new Carrito();
                                car.setItem(item);
                                car.setIdProducto(p.getId());
                                car.setNombres(p.getNombres());
                                car.setDescripcion(p.getDescripcion());
                                car.setPrecioCompra(p.getPrecio());
                                car.setCantidad(cantidad);
                                car.setSubTotal(cantidad * p.getPrecio());
                                listaCarrito.add(car);
                            }
                        } else {
                            item = item + 1;
                            car = new Carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());
                            listaCarrito.add(car);
                        }
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("Total", Total);
                        request.setAttribute("precioEnvio", precioEnvio);
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("carrito", listaCarrito);
                        request.setAttribute("contador", listaCarrito.size());
                        request.getRequestDispatcher("carrito.jsp").forward(request, response);
                        break;
                    case "AgregarCarrito":
                        int pos = 0;
                        cantidad = 1;
                        idp = Integer.parseInt(request.getParameter("id"));
                        p = pdao.listarId(idp);
                        if (listaCarrito.size() > 0) {
                            for (int i = 0; i < listaCarrito.size(); i++) {
                                if (idp == listaCarrito.get(i).getIdProducto()) {
                                    pos = i;
                                }
                            }
                            if (idp == listaCarrito.get(pos).getIdProducto()) {
                                cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                                double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                                listaCarrito.get(pos).setCantidad(cantidad);
                                listaCarrito.get(pos).setSubTotal(subtotal);
                            } else {
                                item = item + 1;
                                car = new Carrito();
                                car.setItem(item);
                                car.setIdProducto(p.getId());
                                car.setNombres(p.getNombres());
                                car.setDescripcion(p.getDescripcion());
                                car.setPrecioCompra(p.getPrecio());
                                car.setCantidad(cantidad);
                                car.setSubTotal(cantidad * p.getPrecio());
                                listaCarrito.add(car);
                            }
                        } else {
                            item = item + 1;
                            car = new Carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());
                            listaCarrito.add(car);
                        }
                        request.setAttribute("contador", listaCarrito.size());
                        request.getRequestDispatcher("srvUsuario?accion=home2").forward(request, response);
                        break;
                    case "Delete":
                        int idproducto = Integer.parseInt(request.getParameter("idp"));
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            if (listaCarrito.get(i).getIdProducto() == idproducto) {
                                listaCarrito.remove(i);
                            }
                        }
                        if (listaCarrito.isEmpty()) {
                            item = 0;
                        }
                        request.getRequestDispatcher("srvUsuario?accion=Carrito").forward(request, response);
                        break;
                    case "Aumentar":
                        totalPagar = 0.0;
                        int ps = 0;
                        cantidad = 1;
                        idp = Integer.parseInt(request.getParameter("idp"));
                        p = pdao.listarId(idp);
                        if (listaCarrito.size() > 0) {
                            for (int i = 0; i < listaCarrito.size(); i++) {
                                if (idp == listaCarrito.get(i).getIdProducto()) {
                                    ps = i;
                                }
                            }
                            if (idp == listaCarrito.get(ps).getIdProducto()) {
                                cantidad = listaCarrito.get(ps).getCantidad() + cantidad;
                                double subtotal = listaCarrito.get(ps).getPrecioCompra() * cantidad;
                                listaCarrito.get(ps).setCantidad(cantidad);
                                listaCarrito.get(ps).setSubTotal(subtotal);
                            } else {
                                item = item + 1;
                                car = new Carrito();
                                car.setItem(item);
                                car.setIdProducto(p.getId());
                                car.setNombres(p.getNombres());
                                car.setDescripcion(p.getDescripcion());
                                car.setPrecioCompra(p.getPrecio());
                                car.setCantidad(cantidad);
                                car.setSubTotal(cantidad * p.getPrecio());
                                listaCarrito.add(car);
                            }
                        } else {
                            item = item + 1;
                            car = new Carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());
                            listaCarrito.add(car);
                        }
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("Total", Total);
                        request.setAttribute("precioEnvio", precioEnvio);
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("carrito", listaCarrito);
                        request.setAttribute("contador", listaCarrito.size());
                        request.getRequestDispatcher("carrito.jsp").forward(request, response);
                        break;
                    case "Disminuir":
                        totalPagar = 0.0;
                        int pss = 0;
                        cantidad = 1;
                        idp = Integer.parseInt(request.getParameter("idp"));
                        p = pdao.listarId(idp);
                        if (listaCarrito.size() > 0) {
                            for (int i = 0; i < listaCarrito.size(); i++) {
                                if (idp == listaCarrito.get(i).getIdProducto()) {
                                    pss = i;
                                }
                            }
                            if (idp == listaCarrito.get(pss).getIdProducto()) {
                                if (listaCarrito.get(pss).getCantidad() <= 1) {
                                    cantidad = 1;
                                } else {
                                    cantidad = listaCarrito.get(pss).getCantidad() - cantidad;
                                }
                                double subtotal = listaCarrito.get(pss).getPrecioCompra() * cantidad;
                                listaCarrito.get(pss).setCantidad(cantidad);
                                listaCarrito.get(pss).setSubTotal(subtotal);
                            } else {
                                item = item + 1;
                                car = new Carrito();
                                car.setItem(item);
                                car.setIdProducto(p.getId());
                                car.setNombres(p.getNombres());
                                car.setDescripcion(p.getDescripcion());
                                car.setPrecioCompra(p.getPrecio());
                                car.setCantidad(cantidad);
                                car.setSubTotal(cantidad * p.getPrecio());
                                listaCarrito.add(car);
                            }
                        } else {
                            item = item + 1;
                            car = new Carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());
                            listaCarrito.add(car);
                        }
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("Total", Total);
                        request.setAttribute("precioEnvio", precioEnvio);
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("carrito", listaCarrito);
                        request.setAttribute("contador", listaCarrito.size());
                        request.getRequestDispatcher("carrito.jsp").forward(request, response);
                        break;
                    case "Carrito":
                        totalPagar = 0.0;
                        Total = 0.0;
                        precioEnvio = 0.0;
                        request.setAttribute("carrito", listaCarrito);
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("Total", Total);
                        request.setAttribute("precioEnvio", precioEnvio);
                        request.setAttribute("totalPagar", totalPagar);
                        request.getRequestDispatcher("carrito.jsp").forward(request, response);
                        break;
                    case "RealizarPago":
                        if (Total != 0) {
                            pag.setDireccion(request.getParameter("direccion"));
                            pag.setNumTarj(Long.parseLong(request.getParameter("cardNumber")));
                            pag.setFechaexp(request.getParameter("cardExpiry"));
                            int cvc = Integer.parseInt(request.getParameter("cardCVC"));
                            if (cvc == 0) {
                                pag.setCvc(Integer.parseInt("0"));
                            } else {
                                pag.setCvc(cvc);
                            }
                            PagoDAO padao = new PagoDAO();
                            int idPago = padao.RegistrarPago(pag);
                            pag.setId(idPago);
                            if (idPago != 0) {
                                totalPagar = 0.0;
                                Total = 0.0;
                                precioEnvio = 0.0;
                                request.setAttribute("carrito", listaCarrito);
                                for (int i = 0; i < listaCarrito.size(); i++) {
                                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                                }
                                if (listaCarrito.size() != 0) {
                                    precioEnvio = 10000;
                                }
                                Total = totalPagar + precioEnvio;
                                request.setAttribute("Total", Total);
                                request.setAttribute("precioEnvio", precioEnvio);
                                request.setAttribute("totalPagar", totalPagar);
                                request.getRequestDispatcher("carrito2.jsp").forward(request, response);
                            } else {
                                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                            }
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "RealizarPago2":
                        if (Total != 0) {
                            pag.setDireccion(request.getParameter("direccion"));
                            pag.setTipCuenta(request.getParameter("tipCuenta"));
                            pag.setNumCuenta(Long.parseLong(request.getParameter("numCuenta")));
                            pag.setBanco(request.getParameter("banco"));
                            PagoDAO padaop = new PagoDAO();
                            int idPago = padaop.RegistrarPago2(pag);
                            pag.setId(idPago);
                            if (idPago != 0) {
                                totalPagar = 0.0;
                                Total = 0.0;
                                precioEnvio = 0.0;
                                request.setAttribute("carrito", listaCarrito);
                                for (int i = 0; i < listaCarrito.size(); i++) {
                                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                                }
                                if (listaCarrito.size() != 0) {
                                    precioEnvio = 10000;
                                }
                                Total = totalPagar + precioEnvio;
                                request.setAttribute("Total", Total);
                                request.setAttribute("precioEnvio", precioEnvio);
                                request.setAttribute("totalPagar", totalPagar);
                                request.getRequestDispatcher("carrito2.jsp").forward(request, response);
                            } else {
                                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                            }
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "GenerarCompra":
                        int idpago = pag.getId();
                        if (Total != 0) {
                            //Actualización del stock
                            for (int i = 0; i < listaCarrito.size(); i++) {
                                Producto pr = new Producto();
                                int canti = listaCarrito.get(i).getCantidad();
                                int idpro = listaCarrito.get(i).getIdProducto();
                                ProductoDAO ao = new ProductoDAO();
                                pr = ao.buscar(idpro);
                                int sac = pr.getStock() - canti;
                                ao.actualizarstock(idpro, sac);
                            }
                            //Hacer compras
                            int id = usuario.getId_usuario();
                            usuario.setId_usuario(id);
                            Compra compra = new Compra(usuario, idpago, Fecha.FechaBD(), Total, "Cancelado", listaCarrito);
                            int res = daoo.GenerarCompra(compra);
                            if (res != 0 && Total > 0) {
                                listaCarrito = new ArrayList<>();
                                CompraDAO cdao = new CompraDAO();
                                List comprass = cdao.misCompras(usuario.getId_usuario());
                                request.setAttribute("myCompras", comprass);
                                request.getRequestDispatcher("compras.jsp").forward(request, response);
                            } else {
                                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                            }
                            //productos=null;
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }

                        break;
                    case "verDetalle":
                        Total = 0.0;
                        int idcompras = Integer.parseInt(request.getParameter("idcompra"));
                        List<DetalleCompra> detalle = daoo.Detalle(idcompras);
                        request.setAttribute("myDetalle", detalle);
                        for (int i = 0; i < detalle.size(); i++) {
                            Total = Total + (detalle.get(i).getPrecioCompra() * detalle.get(i).getCantidad());
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("montoPagar", Total);
                        request.getRequestDispatcher("detalle.jsp").forward(request, response);
                        break;
                    case "verDetalle2":
                        Total = 0.0;
                        int iddcompras = Integer.parseInt(request.getParameter("idcompra"));
                        List<DetalleCompra> detalles = daoo.Detalle(iddcompras);
                        request.setAttribute("myDetalle", detalles);
                        for (int i = 0; i < detalles.size(); i++) {
                            Total = Total + (detalles.get(i).getPrecioCompra() * detalles.get(i).getCantidad());
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("montoPagar", Total);
                        request.getRequestDispatcher("detalle2.jsp").forward(request, response);
                        break;
                    case "verDetalle3":
                        Total = 0.0;
                        int iddcomprass = Integer.parseInt(request.getParameter("idcompra"));
                        List<DetalleCompra> detalless = daoo.Detalle(iddcomprass);
                        request.setAttribute("myDetalle", detalless);
                        for (int i = 0; i < detalless.size(); i++) {
                            Total = Total + (detalless.get(i).getPrecioCompra() * detalless.get(i).getCantidad());
                        }
                        if (listaCarrito.size() != 0) {
                            precioEnvio = 10000;
                        }
                        Total = totalPagar + precioEnvio;
                        request.setAttribute("montoPagar", Total);
                        request.getRequestDispatcher("detallePedido.jsp").forward(request, response);
                        break;
                    case "pago":
                        PagoDAO padao = new PagoDAO();
                        List<Pago> pago = padao.listPago();
                        request.setAttribute("pago", pago);
                        request.getRequestDispatcher("pago.jsp").forward(request, response);
                        break;
                    case "misCompras":
                        listaCarrito = new ArrayList<>();
                        CompraDAO cdao = new CompraDAO();
                        List comprass = cdao.misCompras(usuario.getId_usuario());
                        request.setAttribute("myCompras", comprass);
                        request.getRequestDispatcher("compras.jsp").forward(request, response);
                        break;
                    case "misVentas":
                        CompraDAO comdao = new CompraDAO();
                        compras = comdao.misVentas();
                        request.setAttribute("myVentas", compras);
                        request.getRequestDispatcher("ventas.jsp").forward(request, response);
                        break;
                    case "pedido":
                        CompraDAO compdao = new CompraDAO();
                        compras = compdao.pedido();
                        request.setAttribute("pedido", compras);
                        request.getRequestDispatcher("distribuidor.jsp").forward(request, response);
                        break;
                    case "registrar":
                        carg = new cargo();
                        carg.setCodigo(2);
                        usuario clien = new usuario();
                        clien.setNombreUsuario(request.getParameter("txtusu"));
                        clien.setClave(request.getParameter("txtcon"));
                        clien.setEstado(true);
                        clien.setCargo(carg);
                        clien.setCorreo(request.getParameter("txtcor"));
                        clien.setDireccion(request.getParameter("txtdir"));
                        if (dao.RegistrarCliente(clien) != 0) {
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                        break;
                    case "registrarAD":
                        carg = new cargo();
                        String cargo = request.getParameter("tipCargo");
                        JOptionPane.showMessageDialog(null, cargo);
                        if(cargo.equals("ADMINISTRADOR")){
                            carg.setCodigo(1);
                        }else if(cargo.equals("DISTRIBUIDOR")){
                            carg.setCodigo(3);
                        }
                        usuario dis = new usuario();
                        dis.setNombreUsuario(request.getParameter("txtusu"));
                        dis.setClave(request.getParameter("txtcon"));
                        dis.setEstado(true);
                        dis.setCargo(carg);
                        dis.setCorreo(request.getParameter("txtcor"));
                        dis.setDireccion(request.getParameter("txtdir"));
                        if (dao.RegistrarCliente(dis) != 0) {
                            request.setAttribute("mensaje", "Los datos se guardarón con éxito");
                            request.getRequestDispatcher("exito2.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "Guardar":
                        Producto prod = new Producto();
                        prod.setNombres(request.getParameter("nomp"));
                        Part part = request.getPart("inputfoto");
                        InputStream inputStream = part.getInputStream();
                        prod.setFoto(inputStream);
                        prod.setDescripcion(request.getParameter("descripcion"));
                        prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
                        prod.setStock(Integer.parseInt(request.getParameter("stock")));
                        if (pdao.agregar(prod) != 0) {
                            request.setAttribute("producto", productos);
                            request.getRequestDispatcher("administrador.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "editarC":
                        int id = usuario.getId_usuario();
                        usuario user = dao.getId(id);
                        request.setAttribute("usuario", user);
                        request.getRequestDispatcher("actualizarU.jsp").forward(request, response);
                        break;
                    case "editarA":
                        int idD = usuario.getId_usuario();
                        String nombreCargo = usuario.getCargo().getNombreCargo();
                        usuario user1 = dao.getId(idD);
                        request.setAttribute("usuario", user1);
                        request.setAttribute("nombreCargo", nombreCargo);
                        request.getRequestDispatcher("actualizarA.jsp").forward(request, response);
                        break;
                    case "editarD":
                        int ind = usuario.getId_usuario();
                        String nombreCargos = usuario.getCargo().getNombreCargo();
                        usuario user2 = dao.getId(ind);
                        request.setAttribute("usuario", user2);
                        request.setAttribute("nombreCargo", nombreCargos);
                        request.getRequestDispatcher("actualizarD.jsp").forward(request, response);
                        break;
                    case "actualizarC":
                        carg = new cargo();
                        carg.setCodigo(2);
                        int idd = usuario.getId_usuario();
                        usuario.setId_usuario(idd);
                        usuario.setNombreUsuario(request.getParameter("txtusu"));
                        usuario.setClave(request.getParameter("txtcon"));
                        usuario.setCorreo(request.getParameter("txtcor"));
                        usuario.setDireccion(request.getParameter("txtdir"));
                        if (dao.update(usuario) != 0) {
                            request.getRequestDispatcher("exito.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "actualizarE":
                        int idcom = Integer.parseInt(request.getParameter("id"));
                        Compra comp = new Compra();
                        comp.setEstado("Entregado");
                        comp.setId(idcom);
                        if (daoo.updateE(comp) != 0) {
                            request.setAttribute("mensaje", "Se actualizarón con éxito los datos");
                            request.getRequestDispatcher("exito3.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "actualizarA":
                        carg = new cargo();
                        carg.setCodigo(1);
                        int ida = usuario.getId_usuario();
                        usuario.setId_usuario(ida);
                        usuario.setNombreUsuario(request.getParameter("txtusu"));
                        usuario.setClave(request.getParameter("txtcon"));
                        usuario.setCorreo(request.getParameter("txtcor"));
                        usuario.setDireccion(request.getParameter("txtdir"));
                        if (dao.update(usuario) != 0) {
                            request.setAttribute("mensaje", "Se actualizó con éxito");
                            request.getRequestDispatcher("exito2.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "actualizarD":
                        carg = new cargo();
                        carg.setCodigo(3);
                        int iid = usuario.getId_usuario();
                        usuario.setId_usuario(iid);
                        usuario.setNombreUsuario(request.getParameter("txtusu"));
                        usuario.setClave(request.getParameter("txtcon"));
                        usuario.setCorreo(request.getParameter("txtcor"));
                        usuario.setDireccion(request.getParameter("txtdir"));
                        if (dao.update(usuario) != 0) {
                            request.setAttribute("mensaje", "Se actualizó con éxito");
                            request.getRequestDispatcher("exito3.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "editP":
                        int iddp = Integer.parseInt(request.getParameter("id"));
                        Producto ppao = pdao.getId(iddp);
                        request.setAttribute("producto", ppao);
                        request.getRequestDispatcher("editProducto.jsp").forward(request, response);
                        break;
                    case "actualizarP":
                        Producto prodd = new Producto();
                        Part partt = request.getPart("inputfoto");
                        if (partt != null && partt.getSize() > 0) {
                            prodd.setId(Integer.parseInt(request.getParameter("txtid")));
                            prodd.setNombres(request.getParameter("nomp"));
                            InputStream inputStreamm = partt.getInputStream();
                            prodd.setFoto(inputStreamm);
                            prodd.setDescripcion(request.getParameter("descripcion"));
                            prodd.setPrecio(Double.parseDouble(request.getParameter("precio")));
                            prodd.setStock(Integer.parseInt(request.getParameter("stock")));
                            if (pdao.update(prodd) != 0) {
                                request.setAttribute("mensaje", "Se actualizó con éxito");
                                request.getRequestDispatcher("exito2.jsp").forward(request, response);
                            } else {
                                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                            }
                        } else {
                            prodd.setId(Integer.parseInt(request.getParameter("txtid")));
                            prodd.setNombres(request.getParameter("nomp"));
                            prodd.setDescripcion(request.getParameter("descripcion"));
                            prodd.setPrecio(Double.parseDouble(request.getParameter("precio")));
                            prodd.setStock(Integer.parseInt(request.getParameter("stock")));
                            if (pdao.update2(prodd) != 0) {
                                request.setAttribute("mensaje", "Se actualizó con éxito");
                                request.getRequestDispatcher("exito2.jsp").forward(request, response);
                            } else {
                                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                            }
                        }
                        break;
                    case "deleteP":
                        int iddpr = Integer.parseInt(request.getParameter("id"));
                        //pdao.deletep(iddpr);
                        int res = pdao.deletep(iddpr);
                        if (res != 0) {
                            request.setAttribute("mensaje", "Se eliminó con éxito");
                            request.getRequestDispatcher("exito2.jsp").forward(request, response);
                        } else {
                            request.setAttribute("config", "alert alert-danger");
                            request.setAttribute("mensaje", "Error al eliminar el producto");
                            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                        }
                        break;
                    case "home":
                        request.setAttribute("productos", productos);
                        request.getRequestDispatcher("identificar.jsp").forward(request, response);
                        break;
                    case "home2":
                        request.setAttribute("productos", productos);
                        request.getRequestDispatcher("identificar2.jsp").forward(request, response);
                    case "home3":
                        request.setAttribute("producto", productos);
                        request.getRequestDispatcher("administrador.jsp").forward(request, response);
                        break;
                    default:
                        request.setAttribute("productos", productos);
                        request.getRequestDispatcher("identificar.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("productos", productos);
                //request.getRequestDispatcher("identificar.jsp").forward(request, response);
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);

            } catch (Exception ex) {
                System.out.println("Error" + e.getMessage());
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion;
        DAOUSUARIO dao;
        usuario usuario;
        usuario = this.obtenerUsuario(request);
        dao = new DAOUSUARIO();
        usuario = dao.identificar(usuario);
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            request.setAttribute("msje", "Bienvenido al sistema");
            this.getServletConfig().getServletContext().getRequestDispatcher("/administrador.jsp").forward(request, response);
        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("CLIENTE")) {
            sesion = request.getSession();
            sesion.setAttribute("cliente", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/identificar2.jsp").forward(request, response);
        } else {
            request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }

    }

    private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");

    }

    private usuario obtenerUsuario(HttpServletRequest request) {
        usuario u = new usuario();
        u.setNombreUsuario(request.getParameter("txtUsu"));
        u.setClave(request.getParameter("txtPass"));
        return u;
    }

}
