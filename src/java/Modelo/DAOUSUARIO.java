package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DAOUSUARIO extends conexion {

    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public usuario identificar(usuario user) throws Exception {
        usuario usu = null;
        conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, C.NOMBRECARGO FROM USUARIO U "
                + "INNER JOIN CARGO C ON U.IDCARGO = C.IDCARGO "
                + "WHERE U.ESTADO = 1 AND U.NOMBREUSUARIO = '" + user.getNombreUsuario() + "' "
                + "AND U.CLAVE = '" + user.getClave() + "'";
        con = new conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(user.getNombreUsuario());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setEstado(true);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
            if (st != null && st.isClosed() == false) {
                st.close();

            }
            st = null;
            if (cn != null & cn.isClosed() == false) {
                cn.close();

            }
            cn = null;
        }
        return usu;
    }

    public usuario getId(int id) {
        String sql = "select * from usuario where IDUSUARIO=?";
        usuario user = new usuario();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId_usuario(rs.getInt(1));
                user.setNombreUsuario(rs.getString(2));
                user.setClave(rs.getString(3));
                user.setEstado(rs.getBoolean(4));
                user.setCargo(new cargo());
                user.getCargo().setNombreCargo(rs.getString(5));
                user.setCorreo(rs.getString(6));
                user.setDireccion(rs.getString(7));
            }
        } catch (Exception e) {

        }
        return user;
    }

    public int RegistrarCliente(usuario cliente) {

        String sql = "insert into usuario(NOMBREUSUARIO,CLAVE,ESTADO,IDCARGO,CORREO,DIRECCION)values(?,?,?,?,?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombreUsuario());
            ps.setString(2, cliente.getClave());
            ps.setBoolean(3, cliente.isEstado());
            ps.setInt(4, cliente.getCargo().getCodigo());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getDireccion());
            r = ps.executeUpdate();

        } catch (Exception e) {

        }
        return r;
    }

    public int update(usuario u) {
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
    }
}
