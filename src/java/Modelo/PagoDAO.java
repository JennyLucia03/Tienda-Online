package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int RegistrarPago(Pago pago) {
        String sql = "INSERT INTO pago(direccionEnvio, numTarjeta, fechaExp, cvc) VALUES (?, ?, ?, ?)";
        int id = -1; // inicializar id con valor negativo para manejar errores

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pago.getDireccion());
            ps.setLong(2, pago.getNumTarj());
            ps.setString(3, pago.getFechaexp());
            ps.setInt(4, pago.getCvc());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) { // si se insertó correctamente
                rs = ps.getGeneratedKeys(); // obtener las claves generadas
                if (rs.next()) {
                    id = rs.getInt(1); // obtener el id generado
                }
                rs.close();
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public int RegistrarPago2(Pago pago) {
        String sql = "INSERT INTO pago(direccionEnvio, tipoCuenta, numCuenta, banco) VALUES (?, ?, ?, ?)";
        int id = -1; // inicializar id con valor negativo para manejar errores

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pago.getDireccion());
            ps.setString(2, pago.getTipCuenta());
            ps.setLong(3, pago.getNumCuenta());
            ps.setString(4, pago.getBanco());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) { // si se insertó correctamente
                rs = ps.getGeneratedKeys(); // obtener las claves generadas
                if (rs.next()) {
                    id = rs.getInt(1); // obtener el id generado
                }
                rs.close();
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    /*public List listar() {
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
    }*/
    public List listPago(){
        List<Pago> pago = new ArrayList();
        String sql = "select * from pago";
        try{
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pago pa = new Pago();
                pa.setId(rs.getInt(1));
                pa.setDireccion(rs.getString(2));
                pa.setNumTarj(rs.getLong(3));
                pa.setFechaexp(rs.getString(4));
                pa.setCvc(rs.getInt(5));
                pa.setTipCuenta(rs.getString(6));
                pa.setNumCuenta(rs.getLong(7));
                pa.setBanco(rs.getString(8));
                pago.add(pa);
            }
        }catch(Exception e){
        
        }
        return pago;
    }

}
