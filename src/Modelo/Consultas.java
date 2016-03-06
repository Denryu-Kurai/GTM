package Modelo;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
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
    
    /**
     * **************PARTE ADMINISTRATIVA*******************
     */
    // Usuarios
    public void insertarUsuario(String dni, String nombre, String apellidos, String direccion, int telefono, String rol, String usuario, String contraseña) {
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "insert into personas (Dni,nombre,apellidos,direccion,telefono,rol,usuario,contrasenas) VALUES"
                    + " ('" + dni + "','" + nombre + "','" + apellidos + "','" + direccion + "'," + telefono + ",'" + rol + "','" + usuario + "','" + contraseña + "')";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    } // Inserta un usuario.
    
    public void modificarUsuario(String dni, String nombre, String apellidos, String direccion, int telefono, String rol, String usuario, String contraseña) {
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            String sql;
            sql = "update into personas (Dni,nombre,apellidos,direccion,telefono,rol,usuario,contrasenas) VALUES"
                    + " ('" + dni + "','" + nombre + "','" + apellidos + "','" + direccion + "'," + telefono + ",'" + rol + "','" + usuario + "','" + contraseña + "')";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    } // Modifica un usuario.

    /**
     * **************INSERTAR*******************
     */
    

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

    public void insertarHistorial(String matricula, Date fecha) {
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
    
    public void insertarFactura(Date fecha, String historial,double precio){
        con.abrir();
        try {
            String sql = "insert into facturas (fecha, historial,precio) values (?,?,?)";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.setDate(1, new java.sql.Date(fecha.getTime()));
            s.setInt(2,Integer.valueOf(historial) );
            s.setDouble(3, precio);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }

    public void insertarReparacion(int historial, int servicio) {

        con.abrir();
        try {
            String sql = "insert into reparaciones (historial,servicio,realizado) values (?,?,?)";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.setInt(1, historial);
            s.setInt(2, servicio);
            s.setInt(historial, 0);
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
            rst = stm.executeQuery("select c.modelo,c.marca,c.matricula,p.telefono,c.duenio from coches as c,personas as p where p.dni='" + id + "' && p.dni = c.duenio");
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

    /**
     * ***************GETTERS******************
     */
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
    
    public Double getDinero(String id){
        double a=0;
        con.abrir();
        try {
            Statement s = con.getConexion().createStatement();
            ResultSet rs = s.executeQuery("select precio from servicios where íd ='" + id + "'");
            if (rs.next()) {
                a = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return a;
    }

    public int getIdFechaReciente(String matricula) {
        int actual = 0;
        con.abrir();
        try {
            Statement s = con.getConexion().createStatement();
            ResultSet rs = s.executeQuery("select id from historiales where matricula='" + matricula + "' Order by fecha desc limit 1");
            if (rs.next()) {
                actual = rs.getInt(1);
            }
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
            if (rst.next()) {
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
    
    public String[] getPersona (String dni) {
        
        String[] persona = new String[7];
        
        con.abrir();
        
        try {
            
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select dni, usuario, nombre, apellidos, direccion, telefono, rol from personas where dni = '" + dni +"';");
            
            if (rst.next()) {
                
                for (int i = 1; i <= 7; i++) {
                    
                    persona[i - 1] = rst.getString(i);
                    
                }
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        con.cerrar();
        
        return persona;
        
    }

    public void arreglar(String historial,String servicio){
                con.abrir();
        try {
            String sql = "UPDATE reparaciones SET realizado = 1 where historial='"+historial+"' and servicio="+servicio;
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }
    
    //FINALIZADORES
    public void finPresupuesto(ArrayList a){
       
        
        
    }
    
    /**
     * *********************************
     */
    public DefaultTableModel tablaUsuariosAdministrativo () {
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        
        con.abrir();
        
        try {
            
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select dni, nombre, apellidos from personas");
            
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
        
    }
    
    public DefaultTableModel tablaCochesAdministrativo () {
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        
        modelo.addColumn("Matrícula");
        modelo.addColumn("Propietario");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        
        con.abrir();
        
        try {
            
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("select matricula, duenio, marca, modelo from coches");
            
            while (rst.next()) {
                
                Object[] fila = new Object[4];
                
                for (int i = 1; i <= 4; i++) {
                    
                    fila[i - 1] = rst.getObject(i);
                    
                }
                
                modelo.addRow(fila);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        con.cerrar();
        
        return modelo;
        
    }
    
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
            rst = stm.executeQuery("select c.matricula,c.modelo,c.marca from coches as c,lugares as l where l.matricula=c.matricula");
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
    
    public DefaultTableModel tablaCochesParaPresupuesto() {
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
            rst = stm.executeQuery("select c.matricula,c.modelo,c.marca from coches as c,lugares as l where l.matricula=c.matricula and l.espera = 1");
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

    public DefaultTableModel tablaCochesParaTaller() {
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
            rst = stm.executeQuery("select c.matricula,c.modelo,c.marca from coches as c,lugares as l where l.matricula=c.matricula and l.espera = 0");
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
    
    public ImageIcon abrirImagen(String matricula){
        
        Image pt = null;
        Blob imagen=null;
        con.abrir();
        Connection conexion = con.getConexion();
        try{
        String sql = "SELECT * FROM fotos where matricula='"+matricula+"'";
        
        java.sql.Statement stmt = conexion.createStatement();
        ResultSet results = stmt.executeQuery(sql);
        
        while(results.next())
                
        imagen = results.getBlob("foto");
 
         pt=  javax.imageio.ImageIO.read(imagen.getBinaryStream());
        }catch(Exception e){
            
        }
        con.cerrar();
        ImageIcon icon = new ImageIcon(pt);
  return icon;
 }
   
    public DefaultTableModel tablaServicios(String matricula){
        DefaultTableModel modelo = new DefaultTableModel();     
        modelo.addColumn("MATRICULA");
        modelo.addColumn("ID REPARACIÓN");
        modelo.addColumn("REPARACIÓN");
        
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("Select servicio,nombre from reparaciones as r,servicios as s where r.realizado=0 and s.íd=r.servicio and historial=(Select id from historiales where matricula = '"+matricula+"' Order by fecha desc limit 1)");
            while (rst.next()) {
                Object[] fila = new Object[3];
                fila[0]=matricula;
                for (int i = 1; i <= 2; i++) {
                    fila[i] = rst.getObject(i);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return modelo;
    }
    
    public boolean imagen(String matricula){
            boolean si = false;
            con.abrir();
            Connection conexion = con.getConexion();
            try{
             String sql = "SELECT * FROM fotos where matricula='"+matricula+"'";  
             java.sql.Statement stmt = conexion.createStatement();
        ResultSet results = stmt.executeQuery(sql);
        
        while(results.next())
            si=true;
            }catch(Exception e){
                
            }
            return si;
        }
    
    public DefaultTableModel tablaServicios(boolean a) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            //Este metodo hace que las celdas de la tabla no sean editables al hacer click en ella
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("MARCA");
        modelo.addColumn("MODELO");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("PRECIO");
        con.abrir();
        Connection cn = con.getConexion();
        
        
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery("select * from servicios;");
            while (rs.next()) {
                Object[] fila = new Object[5];
                for (int i = 0; i < 5; i++) {
                    if(a==true && rs.getInt("íd")==7){
                    }else{                   
                    fila[i] = rs.getObject(i + 1);
                    }
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
            
            PreparedStatement pstm = con.getConexion().prepareStatement("insert into fotos(matricula, foto) values(?,?)");
            pstm.setString(1, matricula);
            pstm.setBinaryStream(2, fis, (int) file.length());
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
    
    public void finArreglo(String matricula){
        con.abrir();
        try {
            String sql = "UPDATE lugares SET taller = 0 where matricula='"+matricula+"'";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }
    
    public void finITV(String matricula){
        con.abrir();
        try {
            String sql = "UPDATE lugares SET itv = 0 where matricula='"+matricula+"'";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }
    
    public void finCoche(String matricula){
        con.abrir();
        try {
            String sql = "Delete from lugares where matricula='"+matricula+"'";
            PreparedStatement s = con.getConexion().prepareStatement(sql);
            s.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.cerrar();
    }
    
    
    
    //INTERFAZ CLIENTE
    public String getReparaciones(String matricula){
        String texto="";
        con.abrir();
        try {
            Statement s = con.getConexion().createStatement();
            ResultSet rs = s.executeQuery("Select nombre from reparaciones as r,servicios as s where r.realizado=1 and s.íd=r.servicio and historial=(Select id from historiales where matricula = '"+matricula+"' Order by fecha desc limit 1)");
            while (rs.next()) {               
                System.out.println(rs.getString(1));
                texto = texto+"Se ha realizado con exito el arreglo: "+rs.getString(1)+" en su vehiculo \n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return texto;
    }
    
    public DefaultTableModel tablaCliente(String dni){
         DefaultTableModel modelo = new DefaultTableModel();     
        modelo.addColumn("MATRICULA");       
        con.abrir();
        try {
            stm = con.getConexion().createStatement();
            rst = stm.executeQuery("Select matricula from coches where duenio='"+dni+"'");
            while (rst.next()) {
                Object[] fila = new Object[1];
                
                for (int i = 1; i <= 1; i++) {
                    fila[i-1] = rst.getObject(i);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.cerrar();
        return modelo;
    }
    
}
