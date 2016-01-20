package Controlador;

// @author Denryu Kurai Seishi

import Vista.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ControladorLogin implements ActionListener, MouseListener {
    
    LoginView vista;
    
    public ControladorLogin (LoginView v) {
        
        this.vista = v;
        
    }
    
    public enum AccionMVC {
        
        __Conectar,
        __Registrarse;
        
    }
    
    public void iniciar () {
        
        try {
            
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
            
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}
        
        this.vista.btnConectar.setActionCommand("__Conectar");
        this.vista.btnConectar.addActionListener(this);
        
        this.vista.btnRegistrarse.setActionCommand("__Registrarse");
        this.vista.btnRegistrarse.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            
            case __Conectar:
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
