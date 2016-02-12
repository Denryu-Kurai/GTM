package Controlador;

// @author Denryu Kurai Seishi (Pedro)
// @author Jairo Gallardo
// @author Alfonso Arcos

import Modelo.Consultas;
import Vista.AdministrativoView;
import Vista.LoginView;
import Vista.MecanicoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class ControladorLogin implements ActionListener, MouseListener {
    
    private static ControladorLogin c = null;
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
        __Conectar, __Registrarse,
        
    }

    public void iniciar () {

        // Botones del Login
        vLogin.btnConectar.setActionCommand("__Conectar");
        vLogin.btnConectar.addActionListener(this);
        vLogin.btnRegistrarse.setActionCommand("__Registrarse");
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
                passw = vLogin.txtContraseña.toString();

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
                            
                        default:
                            System.out.println("Error al comparar los roles");
                            break;
                            
                    }
                    
                }
                break;
            case __Registrarse:
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