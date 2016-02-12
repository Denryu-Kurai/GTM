package Controlador;

// @author Denryu Kurai Seishi

import Vista.AdministrativoView;
import Vista.LoginView;
import Vista.MecanicoView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        try {
            
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}
        
        ControladorLogin controlador = ControladorLogin.getControlador();
        
    }
    
}