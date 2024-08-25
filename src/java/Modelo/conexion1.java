package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion1 {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String SERVER_NAME = "DESKTOP-K63RBVF";
    private static final String PORT_NUMBER = "1433";
    private static final String DATABASE_NAME = "verificacion";
    private static final String URL = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER + ";databaseName=" + DATABASE_NAME + ";TrustServerCertificate=True";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    //
    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error al conectar" + e.getMessage());
        }
        return cn;
    }

}
