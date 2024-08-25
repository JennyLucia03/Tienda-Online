package Modelo;

import Modelo.Producto;
import Modelo.conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ProductoDAO {

    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int o;
    int r = 0;

    public Producto buscar(int id) {
        Producto p = null;
        String sql = "select * from producto where idProducto=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public int actualizarstock(int id, int stock) {
        String sql = "update producto set Stock=? where idProducto=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            int r = ps.executeUpdate();
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    public Producto listarId(int id) {
        String sql = "select * from producto where idProducto=" + id;
        Producto p = new Producto();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return p;
    }

    public List listar() {
        List<Producto> productos = new ArrayList();
        String sql = "select * from producto";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
        } catch (Exception e) {

        }
        return productos;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from producto where idProducto=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        //response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {

        }
    }

    public int agregar(Producto p) {
        String sql = "insert into producto (Nombres,Foto,Descripcion,Precio,Stock) values(?,?,?,?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setBlob(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            r = ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }

    public Producto getId(int id) {
        String sql = "select * from producto where idProducto=?";
        Producto pro = new Producto();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setNombres(rs.getString(2));
                pro.setFoto(rs.getBinaryStream(3));
                pro.setDescripcion(rs.getString(4));
                pro.setPrecio(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
            }
        } catch (Exception e) {

        }
        return pro;
    }

    public int update(Producto p) {
        int re = 0;
        String sql = "update producto set Nombres=?,Foto=?,Descripcion=?,Precio=?,Stock=? where idProducto=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setBlob(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId());
            re = ps.executeUpdate();
        } catch (Exception e) {

        }
        return re;
    }

    public int update2(Producto p) {
        int re = 0;
        String sql = "update producto set Nombres=?,Descripcion=?,Precio=?,Stock=? where idProducto=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId());
            re = ps.executeUpdate();
        } catch (Exception e) {

        }
        return re;
    }

    public int deletep(int id) {
        int result = 0;
        try {
            Connection conn = cn.conectar();
            // Buscamos las filas relacionadas en la tabla secundaria
            String sqlDeleteDetalles = "DELETE FROM detalle_compras WHERE idProducto = ?";
            PreparedStatement psDetalles = conn.prepareStatement(sqlDeleteDetalles);
            psDetalles.setInt(1, id);
            int rowsDeletedDetalles = psDetalles.executeUpdate();
            //Compras
            //Eliminar compra
            String sqlCompras = "DELETE FROM compras \n"
                    + "WHERE idCompras NOT IN (\n"
                    + "  SELECT idCompras FROM detalle_compras\n"
                    + ")";
            PreparedStatement psCompras = conn.prepareStatement(sqlCompras);
            int row = psCompras.executeUpdate();
            //Eliminar pago
            String sqlPago = "DELETE FROM pago \n"
                    + "WHERE idPago NOT IN (\n"
                    + "  SELECT idPago FROM compras\n"
                    + ")";
            PreparedStatement psPagos = conn.prepareStatement(sqlPago);
            int row2= psPagos.executeUpdate();
            // Eliminamos la fila en la tabla principal
            String sqlDeleteProducto = "DELETE FROM producto WHERE idProducto = ?";
            PreparedStatement psProducto = conn.prepareStatement(sqlDeleteProducto);
            psProducto.setInt(1, id);
            int rowsDeletedProducto = psProducto.executeUpdate();
            if (rowsDeletedProducto > 0) {
                System.out.println("Se eliminó el producto con ID " + id);
                result = rowsDeletedProducto;
            } else {
                System.out.println("No se encontró un producto con ID " + id);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }

        return result;
    }

}
