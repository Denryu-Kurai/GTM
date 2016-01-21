package Controlador;

// @author Denryu Kurai Seishi

import Vista.AdministrativoView;
import Vista.LoginView;
import Vista.MecanicoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener, MouseListener {
    
    LoginView vistaLogin;
    AdministrativoView vistaAdministrativo;
    MecanicoView vistaMecanico;
    private String user;
    
    public Controlador (LoginView vL, AdministrativoView vA, MecanicoView vM) {
        
        this.vistaLogin = vL;
        this.vistaAdministrativo = vA;
        this.vistaMecanico = vM;
        
    }
    
    public enum AccionMVC {
        
        __Conectar,
        __Registrarse,
        __ACerrarUsuario,
        __ASalir;
        
    }
    
    public void iniciar () {
        
        this.vistaLogin.btnConectar.setActionCommand("__Conectar");
        this.vistaLogin.btnConectar.addActionListener(this);
        
        this.vistaLogin.btnRegistrarse.setActionCommand("__Registrarse");
        this.vistaLogin.btnRegistrarse.addActionListener(this);
        
        this.vistaAdministrativo.btnACerrarUsuario.setActionCommand("__ACerrarUsuario");
        this.vistaAdministrativo.btnACerrarUsuario.addActionListener(this);
        
        this.vistaAdministrativo.btnASalir.setActionCommand("__ASalir");
        this.vistaAdministrativo.btnASalir.addActionListener(this);
        
        this.vistaLogin.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            
            case __Conectar: 
                this.user = vistaLogin.txtUsuario.getText();
                
                if (this.user.equals("Administrativo")) {
                    
                    this.vistaLogin.setVisible(false);
                    this.vistaAdministrativo.setVisible(true);
                    
                } else if (this.user.equals("Mecanico")) {
                    
                    this.vistaLogin.setVisible(false);
                    this.vistaMecanico.setVisible(true);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this.vistaLogin, "Error al conectar. \n"
                            + "Usuarios: \n"
                            + "- Administrativo \n"
                            + "- Mecanico \n"
                            + "Sin tocar contraseña.");
                    
                }
                break;
                
            case __Registrarse:
                break;
                
            case __ACerrarUsuario:
                this.user = null;
                this.vistaLogin.txtUsuario.setText("");
                this.vistaLogin.txtContraseña.setText("");
                
                this.vistaAdministrativo.setVisible(false);
                this.vistaLogin.setVisible(true);
                break;
                
            case __ASalir:
                System.exit(0);
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
