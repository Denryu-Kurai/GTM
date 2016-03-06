package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Modelo.EncriptadorMD5;
import Vista.AdministrativoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
        
        // Prepara la vista.
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
                break;
                
            case __InsertarUsuario:
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
                break;
                
            case __InsertarCoche:
                break;
                
            case __ModificarCoche:
                break;
                
            case __EliminarCoche:
                break;
            
        }
        
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