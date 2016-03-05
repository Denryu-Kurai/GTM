package Controlador;

// @author Denryu Kurai Seishi (Pedro)
// @author Jairo Gallardo
// @author Alfonso Arcos

import Modelo.Consultas;
import Modelo.EncriptadorMD5;
import Vista.AdministrativoView;
import Vista.ClienteView;
import Vista.LoginView;
import Vista.MecanicoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener, MouseListener {
    
    private static ControladorLogin c = null;
    EncriptadorMD5 md5 = new EncriptadorMD5();
    LoginView vLogin;
    Consultas consulta = new Consultas();
    private Date fecha = new Date();
    private String user;
    private String passw;
    
    
    // Patron Singleton
    public static ControladorLogin getInstance () {
        
        if (c == null) {
            
            c = new ControladorLogin(new LoginView());
            c.iniciar();
            
        }
        
        return c;
        
    }

    private ControladorLogin (LoginView vL) {

        this.vLogin = vL;

    }

    public enum AccionMVC {

        // Login
        __Conectar, __Salir;
        
    }

    public void iniciar () {

        // Botones del Login
        vLogin.btnConectar.setActionCommand("__Conectar");
        vLogin.btnConectar.addActionListener(this);
        vLogin.btnRegistrarse.setActionCommand("__Salir");
        vLogin.btnRegistrarse.addActionListener(this);

        // Lanzamos el Login
        vLogin.setVisible(true);

    }
    
    // Metodo para volver a cargar el login
    public void cargar () {
        
        this.user = null;
        this.vLogin.txtUsuario.setText("");
        this.vLogin.txtContraseña.setText("");
        
        this.vLogin.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (AccionMVC.valueOf(e.getActionCommand())) {
            
            /*
             * ***********LOGIN*************
             */
            case __Conectar:
                user = vLogin.txtUsuario.getText();
                passw = md5.encriptarEnMD5(vLogin.txtContraseña.getText());
                
                if (consulta.getLogin(user, passw) == true) {
                    
                    System.out.println("usuario y contraseña correctos");
                    
                    switch (consulta.getRol(user)) {
                        
                        case "mecanico":
                            vLogin.setVisible(false);
                            new ControladorMecanico(new MecanicoView()).iniciar();
                            break;
                            
                        case "administra":
                            vLogin.setVisible(false);
                            new ControladorAdministrativo(new AdministrativoView()).iniciar();
                            break;
                            
                        case "cliente":
                            vLogin.setVisible(false);
                            new ControladorCliente(new ClienteView()).iniciar(user);
                            
                            
                            break;
                            
                        default:
                            System.out.println("Error al comparar los roles");
                            break;
                            
                    }
                    
                } else {
                    
                    JOptionPane.showMessageDialog(vLogin, "Usuario y/o Contraseña incorrectos.");
                    
                }
                break;
            case __Salir:
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