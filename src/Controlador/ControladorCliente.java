/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consultas;
import Vista.ClienteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;

/**
 *
 * @author Alfonso Arcos
 */
public class ControladorCliente implements ActionListener, MouseListener {

    ClienteView vCli ;
    Consultas consulta = new Consultas();
    DefaultListModel modeloLista = new DefaultListModel();
    
    public ControladorCliente (ClienteView vA) {       
        this.vCli = vA;        
    }
    
    public enum ActionMVC {
        
        // Menu
        __ACerrarUsuario    
        
    }
    
    public void iniciar(){
        vCli.jTable1.setModel(consulta.tablaCliente(vCli.dniCliente.getText()));
                
        
                vCli.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
          
                int i = vCli.jTable1.getSelectedRow();
                
                 String matricula = String.valueOf(vCli.jTable1.getValueAt(i, 0));
                 
                 vCli.jTextArea2.setText(consulta.getReparaciones(matricula));
             
            }
        });
        this.vCli.setVisible(true);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
