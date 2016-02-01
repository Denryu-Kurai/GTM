package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

//@author jairo
public class Consultas {

    ConMysql con;
    Statement stm;
    ResultSet rst;

    public Consultas() {
        con = new ConMysql();
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

    public void insertarHistorial(String matricula, ArrayList lista, Date fecha) {
        con.abrir();
        Connection cn = con.getConexion();
        try {
            String sql = "insert into historiales (matricula, fecha) values (?,?)";
            PreparedStatement s = cn.prepareStatement(sql);
            s.setString(1, matricula);
            s.setDate(2, new java.sql.Date(fecha.getTime()));
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertarReparacion(int historial, int servicio) {
        con.abrir();
        Connection cn = con.getConexion();
        try {
            String sql = "insert into reparaciones (historial,servicio) values (?,?)";
            PreparedStatement s = cn.prepareStatement(sql);
            s.setInt(1, historial);
            s.setInt(2, servicio);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Object datosLista(int id) {
        Object[] datos = new Object[8];
        con.abrir();
        try {
            Connection cn = con.getConexion();
            stm = cn.createStatement();
            rst = stm.executeQuery("select c.modelo,c.marca,c.matricula,p.telefono,l.taller,l.pintura,l.itv  from coches as c, lugares as l,personas as p where dni like '" + id + "'");
            while (rst.next()) {
                for (int i = 0; i < 8; i++) {
                    datos[i] = rst.getObject(i);
                    // ASI SE PASA DE DATO  String s = (String)datos[1];
                }

            }
        } catch (Exception e) {

        }
        return datos;
    }

    public int getFechaReciente(String matricula) {
        int actual = 0;
        con.abrir();
        Connection cn = con.getConexion();
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery("select id from historiales where matricula='" + matricula + "' Order by fecha des limit = 1");
            actual = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actual;
    }

    public DefaultTableModel tablaCoches() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("MATRICULA");
        modelo.addColumn("MODELO");
        modelo.addColumn("MARCA");
        con.abrir();
        try {
            Connection cn = con.getConexion();
            stm = cn.createStatement();
            rst = stm.executeQuery("select matricula,modelo,marca from coches");
            while (rst.next()) {
                Object[] fila = new Object[3];
                for (int i = 1; i <= 3; i++) {
                    fila[i - 1] = rst.getObject(i);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return modelo;
    }//tablaCoches

    public DefaultTableModel tablaServicios() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("MARCA");
        modelo.addColumn("MODELO");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("PRECIO");
        con.abrir();
        Connection cn = con.getConexion();
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery("select marca,modelo,nombre,precio from servicios;");
            while (rs.next()) {
                Object[] fila = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public DefaultTableModel tablaServiciosFiltrada(String mar, String mod) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("MARCA");
        modelo.addColumn("MODELO");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("PRECIO");
        con.abrir();
        Connection cn = con.getConexion();
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery("select marca,modelo,nombre,precio from servicios where marca like '%" + mar + "%' and modelo like '%" + mod + "%';");
            while (rs.next()) {
                Object[] fila = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public boolean getLogin(String user, String password) {
        boolean pasa = false;
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select usuario, contrasenas from personas where usuario like '" + user + "' and contrasenas like '" + password + "';");
            if (rst != null) {
                pasa = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pasa;
    }

    public String getRol(String user) {
        String ok = "";
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select rol from personas where usuario like '" + user + "';");
            if (rst.next()) {
                ok = rst.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ok = ok.toLowerCase();
        return ok;
    }

}
