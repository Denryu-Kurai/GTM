package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Vista.MecanicoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorMecanico implements ActionListener, MouseListener {
    
    MecanicoView vMeca;
    Consultas consulta = new Consultas();
    DefaultListModel modeloLista = new DefaultListModel();
    private Date fecha = new Date();
    public ControladorMecanico (MecanicoView vA) {
        
        this.vMeca = vA;
        
    }
    
    public enum ActionMVC {
        
        // Menu
        __ACerrarUsuario, __ASalir,
        
        // Mecanico
        __Listame, __Presupuesteame, __Tallerizame, __Pintame, __ITVme,
        __SubirImagen,__FinPresupuesto;
        
    }
    
    public void iniciar () {
        
        // Acciones del menu
        vMeca.btnCerrarUsuario.setActionCommand("__ACerrarUsuario");
        vMeca.btnCerrarUsuario.addActionListener(this);
        vMeca.btnSalir.setActionCommand("__ASalir");
        vMeca.btnSalir.addActionListener(this);
        
        // Botones del Mecanico
        vMeca.btnListCar.setActionCommand("__Listame");
        vMeca.btnListCar.addActionListener(this);
        vMeca.btnItv.setActionCommand("__ITVme");
        vMeca.btnItv.addActionListener(this);
        vMeca.btnPresup.setActionCommand("__Presupuesteame");
        vMeca.btnPresup.addActionListener(this);
        vMeca.btnTaller.setActionCommand("__Tallerizame");
        vMeca.btnTaller.addActionListener(this);
        vMeca.btnPintura.setActionCommand("__Pintame");
        vMeca.btnPintura.addActionListener(this);
        vMeca.btnUpImage.setActionCommand("__SubirImagen");
        vMeca.btnUpImage.addActionListener(this);
        vMeca.btnEndCar_supPresupuesto.setActionCommand("__FinPresupuesto");
        vMeca.btnEndCar_supPresupuesto.addActionListener(this);

        
        
        // MouseListener de la Tabla mecanica de los coches dentro de un "Lugar"
        vMeca.tablaLista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int i = vMeca.tablaLista.getSelectedRow();
                String o = vMeca.tablaLista.getValueAt(i, 0).toString();
                ArrayList a = consulta.datosLista(consulta.getDni(o));
                vMeca.listaModelo.setText(a.get(0).toString());
                vMeca.listaMarca.setText(a.get(1).toString());
                vMeca.listaPropietario.setText(consulta.getDni(o));
                vMeca.listaTelefono.setText(a.get(3).toString());
                vMeca.listaMatricul.setText(a.get(2).toString());
                ArrayList b = consulta.datosLugar(o);
                vMeca.listaTaller.setSelected((Boolean) b.get(0));
                vMeca.listaPintura.setSelected((Boolean) b.get(1));
                vMeca.listaITV.setSelected((Boolean) b.get(2));
                
                if(vMeca.listaITV.isSelected()==true){
                    vMeca.btnGoItv.enable(true);
                }
                if(vMeca.listaTaller.isSelected()==true){
                    vMeca.btnGoTaller.enable(true);
                }
                if(vMeca.listaPintura.isSelected()==true){
                    vMeca.btnGoPaint.enable(true);
                }
                
            }
        });
        //FORMA DE RECOGER DATOS DE UNA TABLA
        vMeca.jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
          
                int i = vMeca.jTable2.getSelectedRow();
                
                 String o = vMeca.jTable2.getValueAt(i, 0)+" "+vMeca.jTable2.getValueAt(i, 3).toString()+" "+vMeca.jTable2.getValueAt(i, 1).toString()+" "+vMeca.jTable2.getValueAt(i, 2).toString();
                 modeloLista.addElement(o);
                vMeca.jList1.setModel(modeloLista);
                String uno = (vMeca.jTable2.getValueAt(i, 4).toString()) ;
                
                double dos = Double.valueOf(vMeca.txtPrecioFinal_Presup.getText());
               
                double total = Double.valueOf(uno) + dos;
              
                vMeca.txtPrecioFinal_Presup.setText(String.valueOf(total));
            }
        });
        //
        
        vMeca.setVisible(true);
                            
        // Ponemos los paneles en false, los activamos con los botones
        // Layered 1
        vMeca.L1_panelLista.setVisible(true);
        vMeca.L1_panelPresupuesto.setVisible(false);
        vMeca.L1_panelTaller.setVisible(false);
        vMeca.L1_panelPintura.setVisible(false);
        vMeca.L1_panelITV.setVisible(false);

        // Layered 2
        vMeca.L2_panelLista.setVisible(true);
        vMeca.L2_panelPresupuesto.setVisible(false);
        vMeca.L2_panelTaller.setVisible(false);
        vMeca.L2_panelPintura.setVisible(false);
        vMeca.L2_panelITV.setVisible(false);
        
        try {
                                
            // Tabla
            vMeca.tablaLista.setModel(consulta.tablaCoches());
        } catch (Exception ex) {

            ex.printStackTrace();

        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (ActionMVC.valueOf(e.getActionCommand())) {
            
            /**
             * *********************MENU**************************
             */
            case __ACerrarUsuario:
                this.vMeca.setVisible(false);
                
                ControladorLogin cntrl = ControladorLogin.getInstance();
                cntrl.cargar();
                break;
                
            case __ASalir:
                System.exit(0);
                break;
            
            /*
             * ************MECANICO*****************
             */
            case __Listame:
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(true);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __SubirImagen:
                int fila = this.vMeca.tablaLista.getSelectedRow();

                if (fila == -1) {

                    JOptionPane.showMessageDialog(null, "Tiene que seleccionar un coche.");

                } else {

                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "gif", "png", "bmp");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(vMeca);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {

                        consulta.insertImagen((String) this.vMeca.tablaLista.getValueAt(fila, 0), chooser.getSelectedFile().getAbsolutePath());

                    }
                    
                }
                break;
            case __FinPresupuesto:
                consulta.insertarHistorial(vMeca.matriculaPresupuesto.getText(), fecha);
                int id = consulta.getIdFechaReciente(vMeca.matriculaPresupuesto.getText());
                ListModel a = vMeca.jList1.getModel();
                
                for(int i=0;i<a.getSize();i++){
                    String c = (String)a.getElementAt(i);
                    c=(c.substring(0, 1));
                    consulta.insertarReparacion(id, Integer.valueOf(c));
                }
                consulta.insertarFactura(fecha, String.valueOf(id), Double.valueOf(vMeca.txtPrecioFinal_Presup.getText()));
                vMeca.jList1.setModel(new DefaultListModel());
                vMeca.dueñoPresupuesto.setText("");
                vMeca.matriculaPresupuesto.setText("");
                vMeca.txtPrecioFinal_Presup.setText("0");
                
                                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(true);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Presupuesteame:
                vMeca.jTable2.setModel(consulta.tablaServicios());
                int i =vMeca.tablaLista.getSelectedRow();
                vMeca.dueñoPresupuesto.setText(vMeca.listaPropietario.getText());
                vMeca.matriculaPresupuesto.setText(vMeca.listaMatricul.getText());
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(true);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(true);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Tallerizame:
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(true);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(true);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Pintame:
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(true);
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(true);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __ITVme:
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(true);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(true);
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