/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Stephano Sanchez
 */
public class DAOUSUARIO extends conexion {
    public usuario identificar(usuario user) throws Exception{
        usuario usu = null;
        conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, C.NOMBRECARGO FROM USUARIO U "
                + "INNER JOIN CARGO C ON U.IDCARGO = C.IDCARGO "
                + "WHERE U.ESTADO = 1 AND U.NOMBREUSUARIO = '" + user.getNombreUsuario() + "' "
                + "AND U.CLAVE = '"+ user.getClave() + "'";
        con = new conexion();
        try{
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next() == true){
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(user.getNombreUsuario());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setEstado(true);
            }
        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }finally{
            if (rs !=null && rs.isClosed() == false){
                rs.close();
            }
            rs = null;
            if(st!= null && st.isClosed() == false){
                st.close();
                
            }
            st = null;
            if(cn != null & cn.isClosed() == false){
                cn.close();
                
            }
            cn = null;
        }
        return usu;
    }
}
