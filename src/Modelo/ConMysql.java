package Modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author Alfonso Ghost
public class ConMysql {

    private Connection conexion;

    public void abrir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //enclase
            //conexion = DriverManager.getConnection("jdbc:mysql://192.168.48.2:3306/dam3_Taller", "dam3_grupo", "mamon");
            //encasa
            conexion = DriverManager.getConnection("jdbc:mysql://88.26.202.99:3306/dam3_Taller", "dam3_grupo", "mamon");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
