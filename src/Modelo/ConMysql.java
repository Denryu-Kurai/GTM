package Modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// @author Alfonso Ghost
// @autor Denryu Kurai Seishi

public class ConMysql {

    private Connection conexion = null;

    public void abrir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // IP DESDE CLASE
            // conexion = DriverManager.getConnection("jdbc:mysql://192.168.48.2:3306/dam3_Taller", "dam3_grupo", "grupo");

            // IP DESDE CASA
            // conexion = DriverManager.getConnection("jdbc:mysql://88.26.202.99:3306/dam3_Taller", "dam3_grupo", "grupo");
            
            // IP de Raspberry
            conexion = DriverManager.getConnection("jdbc:mysql://90.74.183.48/DAM03", "user_dam03", "grupo");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
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
