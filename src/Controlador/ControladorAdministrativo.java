package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Modelo.EncriptadorMD5;
import Vista.AdministrativoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ControladorAdministrativo implements ActionListener, MouseListener {
    
    AdministrativoView vAdmin;
    Consultas consulta = new Consultas();
    EncriptadorMD5 md5 = new EncriptadorMD5();
    
    public ControladorAdministrativo (AdministrativoView vA) {
        
        this.vAdmin = vA;
        
        
    }
    
    public enum ActionMVC {
        
        // Menu
        __CerrarUsuario, __Salir, __CambiarContraseña,
        
        // Botones
        __SeccionUC, __SeccionServicios,
        __SeccionFacturas, __SeccionInformes,
        
        // Usuarios
        __BuscarUsuarios, __AñadirUsuario, __InsertarUsuario,
        __ModificarUsuario, __EliminarUsuario,
        
        // Coches
        __BuscarCoches, __AñadirCoche, __InsertarCoche,
        __ModificarCoche, __EliminarCoche;
        
    }
    
    public void iniciar () {
        
        // Acciones del menu.
        vAdmin.btnCerrarUsuario.setActionCommand("__CerrarUsuario");
        vAdmin.btnCerrarUsuario.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Salir");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnCambiarContraseña.setActionCommand("__CambiarContraseña");
        vAdmin.btnCambiarContraseña.addActionListener(this);
        
        // Botones del Administrativo.
        vAdmin.btnUC.setActionCommand("__SeccionUC");
        vAdmin.btnUC.addActionListener(this);
        vAdmin.btnServicios.setActionCommand("__SeccionServicios");
        vAdmin.btnServicios.addActionListener(this);
        vAdmin.btnFacturas.setActionCommand("__SeccionFacturas");
        vAdmin.btnFacturas.addActionListener(this);
        vAdmin.btnInformes.setActionCommand("__SeccionInformes");
        vAdmin.btnInformes.addActionListener(this);
        
        // Botones de gestion de usuarios.
        vAdmin.btnUBuscar.setActionCommand("__BuscarUsuarios");
        vAdmin.btnUBuscar.addActionListener(this);
        vAdmin.btnUAñadir.setActionCommand("__AñadirUsuario");
        vAdmin.btnUAñadir.addActionListener(this);
        vAdmin.btnUInsertar.setActionCommand("__InsertarUsuario");
        vAdmin.btnUInsertar.addActionListener(this);
        vAdmin.btnUModificar.setActionCommand("__ModificarUsuario");
        vAdmin.btnUModificar.addActionListener(this);
        vAdmin.btnUEliminar.setActionCommand("__EliminarUsuario");
        vAdmin.btnUEliminar.addActionListener(this);
        
        // Botones de gestion de coches.
        vAdmin.btnCBuscar.setActionCommand("__BuscarCoches");
        vAdmin.btnCBuscar.addActionListener(this);
        vAdmin.btnCAñadir.setActionCommand("__AñadirCoche");
        vAdmin.btnCAñadir.addActionListener(this);
        vAdmin.btnCInsertar.setActionCommand("__InsertarCoche");
        vAdmin.btnCInsertar.addActionListener(this);
        vAdmin.btnCModificar.setActionCommand("__ModificarCoche");
        vAdmin.btnCModificar.addActionListener(this);
        vAdmin.btnCEliminar.setActionCommand("__EliminarCoche");
        vAdmin.btnCEliminar.addActionListener(this);
        
        // Prepara las tablas.
        vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
        vAdmin.tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                
                vAdmin.txtUDNI.setFocusable(false);
                vAdmin.btnUInsertar.setVisible(false);
                vAdmin.btnUModificar.setVisible(true);
                vAdmin.btnUEliminar.setVisible(true);
                
                vAdmin.pnUsuario.setVisible(true);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnBlanco.setVisible(false);
                
                limpiar();
                
                int fila = vAdmin.tblUsuarios.getSelectedRow();
                String id = (String) vAdmin.tblUsuarios.getValueAt(fila, 0);
                String[] persona = consulta.getPersona(id);
                
                vAdmin.txtUDNI.setText(persona[0]);
                vAdmin.txtUUsuario.setText(persona[1]);
                vAdmin.txtUNombre.setText(persona[2]);
                vAdmin.txtUApellidos.setText(persona[3]);
                vAdmin.txtUDireccion.setText(persona[4]);
                vAdmin.txtUTelefono.setText(persona[5]);
                
                if (persona[6].equals("cliente")) {
                    
                    vAdmin.cbURol.setSelectedIndex(0);
                    
                } else if (persona[6].equals("mecanico")) {
                    
                    vAdmin.cbURol.setSelectedIndex(1);
                    
                } else {
                    
                    vAdmin.cbURol.setSelectedIndex(2);
                    
                }
                
            }
        });
        
        vAdmin.tblCoches.setModel(consulta.tablaCochesAdministrativo());
        
        // Prepara la vista Administrativa.
        vAdmin.pnUC.setVisible(true);
        vAdmin.pnBlanco.setVisible(true);
        vAdmin.pnUsuario.setVisible(false);
        vAdmin.pnCoche.setVisible(false);
        vAdmin.pnServicios.setVisible(false);
        vAdmin.pnFacturas.setVisible(false);
        vAdmin.pnInformes.setVisible(false);
        
        vAdmin.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (ActionMVC.valueOf(e.getActionCommand())) {
            
            /**
             * *********************MENU**************************
             */
            case __CerrarUsuario:
                this.vAdmin.setVisible(false);
                
                ControladorLogin cntrl = ControladorLogin.getInstance();
                cntrl.cargar();
                break;
                
            case __Salir:
                System.exit(0);
                break;
                
            case __CambiarContraseña:
                break;
            
            /**
             * *************BOTONES ADMINISTRATIVO******************
             */
            case __SeccionUC:
                vAdmin.pnUC.setVisible(true);
                vAdmin.pnBlanco.setVisible(true);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnServicios.setVisible(false);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionServicios:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnServicios.setVisible(true);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionFacturas:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnServicios.setVisible(false);
                vAdmin.pnFacturas.setVisible(true);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionInformes:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnServicios.setVisible(false);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(true);
                break;
                
            /**
             * *************USUARIOS******************
             */
            case __BuscarUsuarios:
                break;
                
            case __AñadirUsuario:
                limpiar();
                
                vAdmin.txtUDNI.setFocusable(true);
                vAdmin.btnUInsertar.setVisible(true);
                vAdmin.btnUModificar.setVisible(false);
                vAdmin.btnUEliminar.setVisible(false);
                
                vAdmin.pnUsuario.setVisible(true);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnBlanco.setVisible(false);
                break;
                
            case __InsertarUsuario:
                String dni, nombre, apellidos, direccion, usuario, contraseña, rol;
                int telefono, c;
                
                c = 0;
                
                if (vAdmin.txtUDNI.getText().equals("")) c++;
                if (vAdmin.txtUUsuario.getText().equals("")) c++;
                if (vAdmin.txtUNombre.getText().equals("")) c++;
                if (vAdmin.txtUApellidos.getText().equals("")) c++;
                if (vAdmin.txtUDireccion.getText().equals("")) c++;
                if (vAdmin.txtUTelefono.getText().equals("")) c++;
                
                if (c > 0) {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Rellena todos los campos.");
                    
                } else {
                    
                    dni = vAdmin.txtUDNI.getText();
                    usuario = vAdmin.txtUUsuario.getText();
                    nombre = vAdmin.txtUNombre.getText();
                    apellidos = vAdmin.txtUApellidos.getText();
                    direccion = vAdmin.txtUDireccion.getText();
                    telefono = Integer.parseInt(vAdmin.txtUTelefono.getText());
                    
                    consulta.insertarUsuario(dni, nombre, apellidos, direccion, telefono, (String) vAdmin.cbURol.getSelectedItem(), usuario, md5.encriptarEnMD5(dni));
                    
                    vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
                    
                    vAdmin.pnUsuario.setVisible(false);
                    vAdmin.pnCoche.setVisible(false);
                    vAdmin.pnBlanco.setVisible(true);
                    
                }
                break;
                
            case __ModificarUsuario:
                break;
                
            case __EliminarUsuario:
                break;
                
            /**
             * *************COCHES******************
             */
            case __BuscarCoches:
                break;
                
            case __AñadirCoche:
                int fila = this.vAdmin.tblUsuarios.getSelectedRow();
                
                if (fila == -1) {
                    
                    JOptionPane.showMessageDialog(null, "Tiene que seleccionar un usuario.");
                    
                } else {
                    
                    limpiar();
                
                    vAdmin.btnCInsertar.setVisible(true);
                    vAdmin.btnCModificar.setVisible(false);
                    vAdmin.btnCEliminar.setVisible(false);
                
                    vAdmin.pnUsuario.setVisible(false);
                    vAdmin.pnCoche.setVisible(true);
                    vAdmin.pnBlanco.setVisible(false);
                    
                    vAdmin.txtCPropietario.setText((String) vAdmin.tblUsuarios.getValueAt(fila, 0));
                    
                }
                break;
                
            case __InsertarCoche:
                break;
                
            case __ModificarCoche:
                break;
                
            case __EliminarCoche:
                break;
            
        }
        
    }
    
    public void limpiar () {
        
        vAdmin.txtUDNI.setText("");
        vAdmin.txtUUsuario.setText("");
        vAdmin.txtUNombre.setText("");
        vAdmin.txtUApellidos.setText("");
        vAdmin.txtUDireccion.setText("");
        vAdmin.txtUTelefono.setText("");
        vAdmin.cbURol.setSelectedIndex(0);
        
        vAdmin.txtCMatricula.setText("");
        vAdmin.txtCPropietario.setText("");
        vAdmin.txtCMarca.setText("");
        vAdmin.txtCModelo.setText("");
        vAdmin.txtCColor.setText("");
        vAdmin.cbCPlazas.setSelectedIndex(0);
        vAdmin.cbCEjes.setSelectedIndex(0);
        vAdmin.cbCPuertas.setSelectedIndex(0);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}