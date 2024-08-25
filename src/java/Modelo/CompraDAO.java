package Modelo;

import Modelo.Carrito;
import Modelo.Compra;
import Modelo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    //Detalle compras
    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int GenerarCompra(Compra compra) {
        int idcompras;
        String sql = "insert into compras(IDUSUARIO,idPago,FechaCompras,Monto,Estado)values(?,?,?,?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra.getUsuario().getId_usuario());
            ps.setInt(2, compra.getIdpago());
            ps.setString(3, compra.getFecha());
            ps.setDouble(4, compra.getMonto());
            ps.setString(5, compra.getEstado());
            r = ps.executeUpdate();

            //Consulta para identificar la última compra
            sql = "Select @@IDENTITY AS idCompras";
            rs = ps.executeQuery(sql);
            rs.next();
            idcompras = rs.getInt("idCompras");
            rs.close();

            for (Carrito detalle : compra.getDetallecompras()) {
                sql = "insert into detalle_compras(idProducto,idCompras,Cantidad,PrecioCompra)values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idcompras);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                r = ps.executeUpdate();
            }

        } catch (Exception e) {

        }
        return r;
    }

    public List misCompras(int id) {
        List lista = new ArrayList();
        String sql = "select * from compras where IDUSUARIO=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setId(rs.getInt(1));
                usuario usuario = new usuario();
                usuario.setId_usuario(rs.getInt(2));
                com.setUsuario(usuario);
                com.setIdpago(rs.getInt(3));
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                lista.add(com);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List misVentas() {
        List list = new ArrayList();
        String sql = "select C.idCompras, U.NOMBREUSUARIO, C.idPago, C.FechaCompras, C.Monto, C.Estado FROM compras C inner join usuario U on U.IDUSUARIO = C.IDUSUARIO";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setId(rs.getInt(1));
                com.setUsuario(new usuario());
                com.getUsuario().setNombreUsuario(rs.getString(2));
                com.setIdpago(rs.getInt(3));
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                list.add(com);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List pedido() {
        List list = new ArrayList();
        String sql = "select C.idCompras, U.NOMBREUSUARIO, C.FechaCompras, C.Estado FROM compras C inner join usuario U on U.IDUSUARIO = C.IDUSUARIO WHERE C.Estado='Cancelado'";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setId(rs.getInt(1));
                com.setUsuario(new usuario());
                com.getUsuario().setNombreUsuario(rs.getString(2));
                com.setFecha(rs.getString(3));
                com.setEstado(rs.getString(4));
                list.add(com);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List Detalle(int id) {
        List lista = new ArrayList();
        String sql = "select DC.idDetalle, P.Nombres, DC.idCompras, DC.Cantidad, DC.PrecioCompra, C.Monto FROM detalle_compras DC inner join producto P on P.idProducto = DC.idProducto inner join compras C on C.idCompras = DC.idCompras where DC.idCompras =" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleCompra dcom = new DetalleCompra();
                dcom.setId(rs.getInt(1));
                dcom.setProducto(new Producto());
                dcom.getProducto().setNombres(rs.getString(2));
                dcom.setIdcompra(rs.getInt(3));
                dcom.setCantidad(rs.getInt(4));
                dcom.setPrecioCompra(rs.getDouble(5));
                dcom.setCompra(new Compra());
                dcom.getCompra().setMonto(rs.getDouble(6));
                lista.add(dcom);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    /*public int update(usuario u) {
        int re=0;
        String sql = "update usuario set NOMBREUSUARIO=?,CLAVE=?,CORREO=?,DIRECCION=? where IDUSUARIO=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getDireccion());
            ps.setInt(5, u.getId_usuario());
            re = ps.executeUpdate();
        } catch (Exception e) {

        }
        return re;
    }*/
    public int updateE(Compra compra) {
        int result = 0;
        String sql = "UPDATE compras SET Estado = ? WHERE idCompras = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, compra.getEstado());
            ps.setInt(2, compra.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de la compra: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return result;
    }

}
