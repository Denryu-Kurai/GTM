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
        __ACerrarUsuario, __ASalir,
        
        // Administrativo
        __NuevoCliente, __ModificarCliente,
        __NuevoCoche, __ModificarCoche,
        __Factura, __Informes, __Servicios,
        
        // JDialog Administrativo
        __JDNClienteAceptar, __JDNClienteCancelar, __JDNCocheAceptar, __JDNCocheCancelar;
        
    }
    
    public void iniciar () {
        
        // Acciones del menu
        vAdmin.btnCerrarUsuario.setActionCommand("__ACerrarUsuario");
        vAdmin.btnCerrarUsuario.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ASalir");
        vAdmin.btnSalir.addActionListener(this);

        // Botones del Administrativo
        vAdmin.btnClienteAñadir.setActionCommand("__NuevoCliente");
        vAdmin.btnClienteAñadir.addActionListener(this);
        vAdmin.btnClienteModificar.setActionCommand("__ModificarCliente");
        vAdmin.btnClienteModificar.addActionListener(this);
        vAdmin.btnCocheAñadir.setActionCommand("__NuevoCoche");
        vAdmin.btnCocheAñadir.addActionListener(this);
        vAdmin.btnCocheModificar.setActionCommand("__ModificarCoche");
        vAdmin.btnCocheModificar.addActionListener(this);
        vAdmin.btnFacturas.setActionCommand("__Factura");
        vAdmin.btnFacturas.addActionListener(this);
        vAdmin.btnInformes.setActionCommand("__Informes");
        vAdmin.btnInformes.addActionListener(this);
        vAdmin.btnServicios.setActionCommand("__Servicios");
        vAdmin.btnServicios.addActionListener(this);

        // Botones del JDialogNewCliente
        vAdmin.JDNCbtnAceptar.setActionCommand("__JDNClienteAceptar");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        vAdmin.JDNCbtnCancelar.setActionCommand("__JDNClienteCancelar");
        vAdmin.JDNCbtnCancelar.addActionListener(this);

        // Botones del JDialogNewCoche
        vAdmin.btnJDNCocheAceptar.setActionCommand("__JDNCocheAceptar");
        vAdmin.btnJDNCocheAceptar.addActionListener(this);
        vAdmin.btnJDNCocheCancelar.setActionCommand("__JDNCocheCancelar");
        vAdmin.btnJDNCocheCancelar.addActionListener(this);
        
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
                
                ControladorLogin cntrl = ControladorLogin.getInstance();
                cntrl.cargar();
                break;
                
            case __ASalir:
                System.exit(0);
                break;
            
            /**
             * ********ADMINISTRATIVO*********
             */
            case __NuevoCliente:
                this.vAdmin.JDNCDNI.setText("");
                this.vAdmin.JDNCNombre.setText("");
                this.vAdmin.JDNCApellidos.setText("");
                this.vAdmin.JDNCDireccion.setText("");
                this.vAdmin.JDNCTelefono.setText("");
                this.vAdmin.JDNCUsuario.setText("");
                
                this.vAdmin.jDiaNewCliente.pack();
                this.vAdmin.jDiaNewCliente.setLocationRelativeTo(null);
                this.vAdmin.jDiaNewCliente.setVisible(true);
                break;
                
            case __ModificarCliente:
                break;
                
            case __NuevoCoche:
                int cliente = this.vAdmin.lstClientes.getSelectedIndex();
                
                if (cliente == -1) {
                    
                    JOptionPane.showMessageDialog(null, "Error. Selecciona antes a un cliente.");
                    
                } else {
                    
                    String matricula, dni, marca, modelo, color;
                    int puertas, plazas, ejes;
                    
                    cliente++;
                    
                    this.vAdmin.txtJDNCocheMatricula.setText("");
                    // this.vAdmin.lblJDNCocheDNI.setText(consulta.getDni());
                    this.vAdmin.txtJDNCocheMarca.setText("");
                    this.vAdmin.txtJDNCocheModelo.setText("");
                    this.vAdmin.txtJDNCocheColor.setText("");
                    this.vAdmin.cbJDNCochePuertas.setSelectedIndex(0);
                    this.vAdmin.cbJDNCochePlazas.setSelectedIndex(0);
                    this.vAdmin.cbJDNCocheEjes.setSelectedIndex(0);
                    
                }
                break;
                
            case __ModificarCoche:
                break;

            case __Servicios:
                break;
                
            case __Informes:
                break;
                
            case __Factura:
                break;
                
            // Dialogs
            case __JDNClienteAceptar:
                String dni, nombre, apellidos, direccion, usuario, rol, contraseña;
                int telefono;
                
                dni = this.vAdmin.JDNCDNI.getText();
                nombre = this.vAdmin.JDNCNombre.getText();
                apellidos = this.vAdmin.JDNCApellidos.getText();
                direccion = this.vAdmin.JDNCDireccion.getText();
                telefono = Integer.parseInt(this.vAdmin.JDNCTelefono.getText());
                usuario = this.vAdmin.JDNCUsuario.getText();
                rol = this.vAdmin.JDNClblRol.getText();
                contraseña = md5.encriptarEnMD5(dni); // La contraseña por defecto es el dni.
                
                try {
                    
                    consulta.insertarCliente(dni, nombre, apellidos, direccion, telefono, rol, usuario, contraseña);
                    this.vAdmin.jDiaNewCliente.dispose();
                
                } catch (Exception ex) {
                    
                    JOptionPane.showMessageDialog(null, "Error al introducir datos en la base de datos.");
                    
                }
                break;
                
            case __JDNClienteCancelar:
                this.vAdmin.jDiaNewCliente.dispose();
                break;
                
            case __JDNCocheAceptar:
                break;
                
            case __JDNCocheCancelar:
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