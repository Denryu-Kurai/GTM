package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

//@author jairo
public class Consultas {

    ConMysql con;
    Statement stm;
    ResultSet rst;

    public Consultas() {
        this.con = new ConMysql();
    }

    public void insertCliente(String Dni, String name, String apell, String direcc, int tlfn) {
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "insert into personas (Dni,nombre,apellidos,direccion,telefono,rol,usuario,contrasenas) VALUES"
                    + " (" + Dni + "," + name + "," + apell + "," + direcc + "," + tlfn + ",'Cliente',null,null)";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//insertarCliente

    public void insertCoche(String matric, String marca, String modelo, String color, String plazas, int ejes, int puertas, String dniduenio) {
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "insert into coches (matricula,marca,modelo,color,plazas,eje,puertas,foto,duenio) VALUES "
                    + "(" + matric + "," + marca + "," + modelo + "," + color + "," + plazas + "," + ejes + "," + puertas + ",null," + dniduenio + ")";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//insertarCoche
    
    
    
    public DefaultTableModel tablaCoches(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("MATRICULA");
        modelo.addColumn("MODELO");
        modelo.addColumn("MARCA");
        
         con.abrir();
        Connection cn = con.getConexion();
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery("select matricula,modelo,marca from coche;");
            while (rs.next()) {
                Object[] fila = new Object[3];
                for (int i = 0; i < 3; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();  
        return modelo;
    }//tablaCoches
    
    
}
