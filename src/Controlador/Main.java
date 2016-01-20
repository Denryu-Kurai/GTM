package Controlador;

// @author Denryu Kurai Seishi

import Vista.LoginView;

public class Main {

    public static void main(String[] args) {
        
        new ControladorLogin(new LoginView()).iniciar();
        
    }
    
}
