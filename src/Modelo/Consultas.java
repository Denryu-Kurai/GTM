package Modelo;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

// @author Denryu Kurai Seishi (Pedro)
// @author Jairo Gallardo
// @author Alfonso Arcos

public class Consultas {

    ConMysql con;
    Statement stm;
    ResultSet rst;

    public Consultas() {
        con = new ConMysql();
        con.abrir();
    }

    //INSERTAR
    public void insertCliente(String Dni, String name, String apell, String direcc, int tlfn) {
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "insert into personas (Dni,nombre,apellidos,direccion,telefono,rol,usuario,contrasenas) VALUES"
                    + " (" + Dni + "," + name + "," + apell + "," + direcc + "," + tlfn + ",'Cliente',null,null)";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }//insertarCliente

    public void insertCoche(String matric, String marca, String modelo, String color, String plazas, int ejes, int puertas, String dniduenio) {
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "insert into coches (matricula,marca,modelo,color,plazas,eje,puertas,foto,duenio) VALUES "
                    + "(" + matric + "," + marca + "," + modelo + "," + color + "," + plazas + "," + ejes + "," + puertas + ",null," + dniduenio + ")";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }//insertarCoche

    public void insertarHistorial(String matricula, ArrayList lista, Date fecha) {
        con.abrir();
        try {
            String sql = "insert into historiales (matricula, fecha) values (?,?)";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.setString(1, matricula);
            s.setDate(2, new java.sql.Date(fecha.getTime()));
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }

    public void insertarReparacion(int historial, int servicio) {

        con.abrir();
        try {
            String sql = "insert into reparaciones (historial,servicio) values (?,?)";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.setInt(1, historial);
            s.setInt(2, servicio);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }

    public ArrayList datosLista(String id) {
        ArrayList<String> datos = new ArrayList<>();
        con.abrir();
        try {
            Connection cn = con.getConexion();
            stm = cn.createStatement();
            rst = stm.executeQuery("select c.modelo,c.marca,c.matricula,p.telefono,c.duenio from coches as c,personas as p where p.dni='" + id + "'");
            while (rst.next()) {
                for (int i = 0; i < 4; i++) {
                    datos.add(rst.getString(i + 1));
                }
            }
        } catch (Exception e) {
        }
        con.cerrar();
        return datos;
    }

    public ArrayList datosLugar(String id) {

        ArrayList<Boolean> datos = new ArrayList<>();
        con.abrir();
        try {
            Connection cn = con.getConexion();
            stm = cn.createStatement();
            rst = stm.executeQuery("Select taller,pintura,itv from lugares where matricula='" + id + "'");
            while (rst.next()) {
                for (int i = 0; i < 3; i++) {
                    datos.add(rst.getBoolean(i + 1));
                }

            }
        } catch (Exception e) {
        }
        con.cerrar();
        return datos;
    }

    //GETTERs
    public String getDni(String id) {
        String actual = null;
        con.abrir();
        try {
            Statement s = con.getConexion().createStatement();
            ResultSet rs = s.executeQuery("select duenio from coches where matricula='" + id + "'");
            if (rs.next()) {
                actual = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return actual;
    }

    public int getFechaReciente(String matricula) {
        int actual = 0;
        con.abrir();
        try {
            Statement s = con.getConexion().createStatement();
            ResultSet rs = s.executeQuery("select id from historiales where matricula='" + matricula + "' Order by fecha des limit = 1");
            actual = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return actual;
    }

    public boolean getLogin(String user, String password) {
        boolean pasa = false;
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select usuario, contrasenas from personas where usuario like '" + user + "' and contrasenas = '" + password + "';");
            if (rst != null) {
                pasa = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
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
        con.cerrar();
        return ok;
    }

    /**
     * *********************************
     */
    public DefaultTableModel tablaCoches() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        modelo.addColumn("MATRICULA");
        modelo.addColumn("MODELO");
        modelo.addColumn("MARCA");
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
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
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
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
        con.cerrar();
        return modelo;
    }

    public DefaultTableModel tablaServiciosFiltrada(String mar, String mod) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
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
        con.cerrar();
        return modelo;
    }
    
    public void insertImagen(String matricula, String url) {
        
        con.abrir();
        FileInputStream fis = null;
        
        try {
            
            File file = new File(url);
            fis = new FileInputStream(file);
            PreparedStatement pstm = con.getConexion().prepareStatement("insert into fotos(matricula, foto) values(" + matricula + ",?)");
            pstm.setBinaryStream(1, fis, (int) file.length());
            pstm.execute();
            pstm.close();
            
        } catch (FileNotFoundException | SQLException ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            try {
                
                fis.close();
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
                
            }
            
        }
        
        con.cerrar();
        
    }

    /* public Image abrirImagen(String matricula) throws SQLException, IOException {
    
     Image img=null;
     String sql = "SELECT * FROM imagen limit 1";
        
     Statement stmt = conexion.createStatement();
     ResultSet results = stmt.executeQuery(sql);
 
     Blob imagen=null;
     while(results.next())
     Blob imagen = results.getBlob("Imagen");
 
     rpta= javax.imageio.ImageIO.read(imagen.getBinaryStream());
     //Esta parte es clave, donde se convierte a imagen
     return rpta;
    
     } */
}
