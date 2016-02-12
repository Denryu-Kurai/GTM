package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Vista.AdministrativoView;
import Vista.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ControladorAdministrativo implements ActionListener, MouseListener {
    
    AdministrativoView vAdmin;
    Consultas consulta = new Consultas();
    
    public ControladorAdministrativo (AdministrativoView vA) {
        
        this.vAdmin = vA;
        
    }
    
    public enum ActionMVC {
        
        // Menu
        __ACerrarUsuario, __ASalir,
        
        // Administrativo
        __NewCliente, __ModCliente,
        __NewCoche, __ModCoche,
        __Factura, __Informes, __Servicios,
        
        // JDialog Administrativo
        __JdNcliAcepta, __JdNcliCancela, __JdNcoConfirma, __JdNcoCancelar;
        
    }
    
    public void iniciar () {
        
        // Acciones del menu
        vAdmin.btnCerrarUsuario.setActionCommand("__ACerrarUsuario");
        vAdmin.btnCerrarUsuario.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ASalir");
        vAdmin.btnSalir.addActionListener(this);

        // Botones del Administrativo
        vAdmin.btnSalir.setActionCommand("__NewCliente");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ModCliente");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__NewCoche");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ModCoche");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Factura");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Informes");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Servicios");
        vAdmin.btnSalir.addActionListener(this);

        // Botones del JDialogNewCliente
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcliAcepta");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcliCancela");
        vAdmin.JDNCbtnAceptar.addActionListener(this);

        // Botones del JDialogNewCoche
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcoConfirma");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcoCancelar");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        
        vAdmin.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (ActionMVC.valueOf(e.getActionCommand())) {
            
            /**
             * *********************MENU**************************
             */
            case __ACerrarUsuario:
                this.vAdmin.setVisible(false);
                
                ControladorLogin cntrl = ControladorLogin.getControlador();
                cntrl.cargar();
                break;
                
            case __ASalir:
                System.exit(0);
                break;
            
            /**
             * ********ADMINISTRATIVO*********
             */
            case __NewCliente:
                break;
                
            case __ModCliente:
                break;
                
            case __NewCoche:
                break;
                
            case __ModCoche:
                break;

            case __Servicios:
                break;
                
            case __Informes:
                break;
                
            case __Factura:
                break;
                
            // Dialogs
            case __JdNcliAcepta:
                break;
                
            case __JdNcliCancela:
                break;
                
            case __JdNcoConfirma:
                break;
                
            case __JdNcoCancelar:
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