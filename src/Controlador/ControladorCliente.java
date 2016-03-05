/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consultas;
import Vista.ClienteView;
import Vista.LoginView;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Alfonso Arcos
 */
public class ControladorCliente implements ActionListener, MouseListener {
    
    ClienteView vCli ;
    Consultas consulta = new Consultas();
    DefaultListModel modeloLista = new DefaultListModel();
    LoginView v;
    public ControladorCliente (ClienteView vA) {       
        this.vCli = vA;        
    }
    
    public enum ActionMVC {
        
        // Menu
        __ACerrarUsuario    
        
    }

    public void iniciar(String user){
        this.vCli.setVisible(true);
        vCli.jTextArea2.setLineWrap(true);
        vCli.dniCliente.setText(user);
        vCli.jTable1.setModel(consulta.tablaCliente(vCli.dniCliente.getText()));
                
        
                vCli.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
          
                int i = vCli.jTable1.getSelectedRow();
                
                 String matricula = String.valueOf(vCli.jTable1.getValueAt(i, 0));
                 vCli.jTextArea2.setText("");
                 vCli.jTextArea2.setText(consulta.getReparaciones(matricula));
             if(consulta.imagen(vCli.dniCliente.getText())==false){
                    ImageIcon fot = new ImageIcon("C:\\Users\\Alfonso Arcos\\Documents\\NetBeansProjects\\GTM\\src\\imagenes\\coche.png");
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vCli.jLabel1.getWidth(), vCli.jLabel1.getHeight(), Image.SCALE_DEFAULT));
                    vCli.jLabel1.setIcon(icono);                  
                    vCli.jLabel1.setText("");
                }else{
                    ImageIcon fot = consulta.abrirImagen(vCli.dniCliente.getText());
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vCli.jLabel1.getWidth(), vCli.jLabel1.getHeight(), Image.SCALE_DEFAULT));
                    vCli.jLabel1.setIcon(icono);                                      
                }
            }
        });
        
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
