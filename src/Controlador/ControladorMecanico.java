package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Vista.MecanicoView;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        __Listame, __Presupuesteame, __Tallerizame, __Pintame, __ITVme,__FinITV,__FinCoche,
        __SubirImagen,__FinPresupuesto,__FinArreglo,__ITV1,__ITV2,__ITV3,__ArreglarParte,__QuitarLista;
        
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
        vMeca.btnGoItv.setActionCommand("__ITVme");
        vMeca.btnGoItv.addActionListener(this);
        vMeca.btnGoPresup.setActionCommand("__Presupuesteame");
        vMeca.btnGoPresup.addActionListener(this);
        vMeca.btnGoTaller.setActionCommand("__Tallerizame");
        vMeca.btnGoTaller.addActionListener(this);
        vMeca.btnUpImage.setActionCommand("__SubirImagen");
        vMeca.btnUpImage.addActionListener(this);
        vMeca.btnEndCar_supPresupuesto.setActionCommand("__FinPresupuesto");
        vMeca.btnEndCar_supPresupuesto.addActionListener(this);
        vMeca.btnQuitarLista.setActionCommand("__QuitarLista");
        vMeca.btnQuitarLista.addActionListener(this);
        vMeca.btnEndCar_supITV1.setActionCommand("__ITV1");
        vMeca.btnEndCar_supITV1.addActionListener(this);
        vMeca.btnEndCar_supITV2.setActionCommand("__ITV2");
        vMeca.btnEndCar_supITV2.addActionListener(this);
        vMeca.btnEndCar_supITV3.setActionCommand("__ITV3");
        vMeca.btnEndCar_supITV3.addActionListener(this);
        vMeca.jButton12.setActionCommand("__ArreglarParte");
        vMeca.jButton12.addActionListener(this);
        vMeca.btnEndCar_supTaller.setActionCommand("__FinArreglo");
        vMeca.btnEndCar_supTaller.addActionListener(this);
        vMeca.btnEndCar_supITV.setActionCommand("__FinITV");
        vMeca.btnEndCar_supITV.addActionListener(this);
        vMeca.btnEndCar_supLista.setActionCommand("__FinCoche");
        vMeca.btnEndCar_supLista.addActionListener(this);
        //INICIO
        vMeca.btnUpImage.setEnabled(false);vMeca.btnGoItv.setEnabled(false);
        vMeca.btnGoTaller.setEnabled(false);vMeca.btnEndCar_supLista.setEnabled(false);
        
        // MouseListener de la Tabla mecanica de los coches dentro de un "Lugar"
        vMeca.tablaLista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vMeca.btnUpImage.setEnabled(false);vMeca.btnGoItv.setEnabled(false);
                vMeca.btnGoTaller.setEnabled(false);vMeca.btnEndCar_supLista.setEnabled(false);
                limpiar();
                
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
                if (vMeca.jComboBox1.getSelectedItem().toString().equals("Para Arreglar")) {
                if(vMeca.listaITV.isSelected()){
                    vMeca.btnGoItv.setEnabled(true);
                }
                if(vMeca.listaTaller.isSelected()){
                    vMeca.btnGoTaller.setEnabled(true);
                }
               
                if(consulta.imagen(vMeca.listaMatricul.getText())==false){
                    ImageIcon fot = new ImageIcon("C:\\Users\\Alfonso Arcos\\Documents\\NetBeansProjects\\GTM\\src\\imagenes\\coche.png");
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vMeca.jLabel2.getWidth(), vMeca.jLabel2.getHeight(), Image.SCALE_DEFAULT));
                    vMeca.jLabel2.setIcon(icono);                  
                    vMeca.jLabel2.setText("");
                }else{
                    ImageIcon fot = consulta.abrirImagen(vMeca.listaMatricul.getText());
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vMeca.jLabel2.getWidth(), vMeca.jLabel2.getHeight(), Image.SCALE_DEFAULT));
                    vMeca.jLabel2.setIcon(icono);                                      
                }
                }else if(vMeca.jComboBox1.getSelectedItem().toString().equals("Para presupuesto")) {
                     vMeca.tablaLista.setModel(consulta.tablaCochesParaPresupuesto());
                     limpiar();
                     vMeca.btnGoPresup.setEnabled(true);
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
        
        // LISTENER PARA EL COMBOBOX
            vMeca.jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                 if (vMeca.jComboBox1.getSelectedItem().toString().equals("Todos los coches")) {
                     vMeca.tablaLista.setModel(consulta.tablaCoches());
                     limpiar();
                     vMeca.btnGoPresup.setEnabled(false);
                 }
                  if (vMeca.jComboBox1.getSelectedItem().toString().equals("Para presupuesto")) {
                     vMeca.tablaLista.setModel(consulta.tablaCochesParaPresupuesto());
                     limpiar();
                     
                 }
                   if (vMeca.jComboBox1.getSelectedItem().toString().equals("Para Arreglar")) {
                     vMeca.tablaLista.setModel(consulta.tablaCochesParaTaller());
                     limpiar();
                     vMeca.btnGoPresup.setEnabled(false);
                 }
            }
        });
        vMeca.setVisible(true);
                            
        // Ponemos los paneles en false, los activamos con los botones
        // Layered 1
        vMeca.L1_panelLista.setVisible(true);
        vMeca.L1_panelPresupuesto.setVisible(false);
        vMeca.L1_panelTaller.setVisible(false);
       
        vMeca.L1_panelITV.setVisible(false);

        // Layered 2
        vMeca.L2_panelLista.setVisible(true);
        vMeca.L2_panelPresupuesto.setVisible(false);
        vMeca.L2_panelTaller.setVisible(false);
        
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
            case __QuitarLista:
                int l = vMeca.jList1.getSelectedIndex();
            
                
                String op = vMeca.jList1.getSelectedValue();
                op=(op.substring(0, 1));  
                double resta = Double.valueOf(vMeca.txtPrecioFinal_Presup.getText())-consulta.getDinero(op);
                vMeca.txtPrecioFinal_Presup.setText(String.valueOf(resta));
               modeloLista.remove(l);
                
                
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
                
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                
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

                        consulta.insertImagen(vMeca.listaMatricul.getText(), chooser.getSelectedFile().getAbsolutePath());

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
                limpiar();
                                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(true);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                
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
                
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(true);
                vMeca.L2_panelTaller.setVisible(false);
                
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Tallerizame:
                vMeca.jTable5.setModel(consulta.tablaServicios(vMeca.listaMatricul.getText()));
                if(this.vMeca.jTable5.getRowCount()==0 ){
                   vMeca.jButton12.setEnabled(false);
                   vMeca.btnEndCar_supTaller.setEnabled(true);
                }
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(true);
                
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(true);
                
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Pintame:
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
               
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __ITVme:
                
                vMeca.jTabbedPane1.setEnabledAt(1, false);
                vMeca.jTabbedPane1.setEnabledAt(2, false);
                
                // Paneles Layered 1
                
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                
                vMeca.L1_panelITV.setVisible(true);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                
                vMeca.L2_panelITV.setVisible(true);
                break;
            case __ITV1:
                itv1();
                break;
            case __ITV2:
                itv2();
                break;
            case __ITV3:
                itv3();
                break;
            case __ArreglarParte:
               int filas= vMeca.jTable5.getSelectedRow();
               String mat =String.valueOf( vMeca.jTable5.getValueAt(filas,0));
               String sev= String.valueOf( vMeca.jTable5.getValueAt(filas, 1));
               consulta.arreglar(String.valueOf(consulta.getIdFechaReciente(mat)),sev );
               vMeca.jTable5.setModel(consulta.tablaServicios(mat));
               if(this.vMeca.jTable5.getRowCount()==0 ){
                   vMeca.jButton12.setEnabled(false);
                   vMeca.btnEndCar_supTaller.setEnabled(true);
                }
                break;
            case __FinArreglo:
                System.out.println("GOLA");
                consulta.finArreglo(vMeca.listaMatricul.getText());
                vMeca.listaTaller.setSelected(false);
                vMeca.btnGoTaller.setEnabled(false);
                // Paneles Layered 1
                vMeca.L1_panelLista.setVisible(true);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                
                vMeca.L1_panelITV.setVisible(false);
                
                // Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                
                vMeca.L2_panelITV.setVisible(false);
                comprobarFin();
                break;
            case __FinITV:
                consulta.finITV(vMeca.listaMatricul.getText());
                vMeca.listaITV.setSelected(false);
                vMeca.btnGoItv.setEnabled(false);
                limpiarITV();
                comprobarFin();
                
                break;
            case __FinCoche:
                consulta.finCoche(vMeca.listaMatricul.getText());
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
    
    void comprobarFin(){
        if(vMeca.listaITV.isSelected() || vMeca.listaTaller.isSelected() || vMeca.listaPintura.isSelected()){
            return;
        }else{
            vMeca.btnEndCar_supLista.setEnabled(true);
        }
    }
    
    void limpiarITV(){
       vMeca.jCheckBox1.setSelected(false);vMeca.jCheckBox24.setSelected(false);
       vMeca.jCheckBox2.setSelected(false);vMeca.jCheckBox25.setSelected(false);
       vMeca.jCheckBox3.setSelected(false);vMeca.jCheckBox26.setSelected(false);
       vMeca.jCheckBox4.setSelected(false);vMeca.jCheckBox27.setSelected(false);
       vMeca.jCheckBox5.setSelected(false);vMeca.jCheckBox28.setSelected(false);
       vMeca.jCheckBox6.setSelected(false);vMeca.jCheckBox29.setSelected(false);
       vMeca.jCheckBox7.setSelected(false);vMeca.jCheckBox30.setSelected(false);
       vMeca.jCheckBox8.setSelected(false);vMeca.jCheckBox31.setSelected(false);
       vMeca.jCheckBox9.setSelected(false);vMeca.jCheckBox32.setSelected(false);
       vMeca.jCheckBox10.setSelected(false);vMeca.jCheckBox33.setSelected(false);
       vMeca.jCheckBox11.setSelected(false);vMeca.jCheckBox34.setSelected(false);
       vMeca.jCheckBox12.setSelected(false);vMeca.jCheckBox35.setSelected(false);
       vMeca.jCheckBox13.setSelected(false);vMeca.jCheckBox36.setSelected(false);
       vMeca.jCheckBox14.setSelected(false);vMeca.jCheckBox37.setSelected(false);
       vMeca.jCheckBox15.setSelected(false);vMeca.jCheckBox38.setSelected(false);
       vMeca.jCheckBox16.setSelected(false);vMeca.jCheckBox39.setSelected(false);
       vMeca.jCheckBox17.setSelected(false);vMeca.jCheckBox40.setSelected(false);
       vMeca.jCheckBox18.setSelected(false);vMeca.jCheckBox41.setSelected(false);
       vMeca.jCheckBox19.setSelected(false);vMeca.jCheckBox42.setSelected(false);
       vMeca.jCheckBox20.setSelected(false);vMeca.jCheckBox43.setSelected(false);
       vMeca.jCheckBox21.setSelected(false);vMeca.jCheckBox44.setSelected(false);
       vMeca.jCheckBox22.setSelected(false);vMeca.jCheckBox45.setSelected(false);
       vMeca.jCheckBox23.setSelected(false);vMeca.jCheckBox46.setSelected(false);
       vMeca.jCheckBox47.setSelected(false);vMeca.jCheckBox58.setSelected(false);
       vMeca.jCheckBox48.setSelected(false);vMeca.jCheckBox59.setSelected(false);
       vMeca.jCheckBox49.setSelected(false);vMeca.jCheckBox60.setSelected(false);
       vMeca.jCheckBox50.setSelected(false);vMeca.jCheckBox61.setSelected(false);
       vMeca.jCheckBox51.setSelected(false);vMeca.jCheckBox62.setSelected(false);
       vMeca.jCheckBox52.setSelected(false);vMeca.jCheckBox63.setSelected(false);
       vMeca.jCheckBox53.setSelected(false);vMeca.jCheckBox64.setSelected(false);
       vMeca.jCheckBox54.setSelected(false);vMeca.jCheckBox65.setSelected(false);
       vMeca.jCheckBox55.setSelected(false);vMeca.jCheckBox66.setSelected(false);
       vMeca.jCheckBox56.setSelected(false);vMeca.jCheckBox67.setSelected(false);
       vMeca.jCheckBox57.setSelected(false);vMeca.jCheckBox68.setSelected(false);
       vMeca.jCheckBox69.setSelected(false);vMeca.jCheckBox80.setSelected(false);
       vMeca.jCheckBox70.setSelected(false);vMeca.jCheckBox81.setSelected(false);
       vMeca.jCheckBox71.setSelected(false);vMeca.jCheckBox82.setSelected(false);
       vMeca.jCheckBox72.setSelected(false);vMeca.jCheckBox83.setSelected(false);
       vMeca.jCheckBox73.setSelected(false);vMeca.jCheckBox84.setSelected(false);
       vMeca.jCheckBox74.setSelected(false);vMeca.jCheckBox85.setSelected(false);
       vMeca.jCheckBox75.setSelected(false);vMeca.jCheckBox86.setSelected(false);
       vMeca.jCheckBox76.setSelected(false);vMeca.jCheckBox79.setSelected(false);
       vMeca.jCheckBox77.setSelected(false);vMeca.jCheckBox78.setSelected(false);
       
      
       
       
    }
    
    void limpiar(){
                vMeca.listaModelo.setText("");
                vMeca.listaMarca.setText("");
                vMeca.listaPropietario.setText("");
                vMeca.listaTelefono.setText("");
                vMeca.listaMatricul.setText("");
                
                vMeca.listaTaller.setSelected(false);
                vMeca.listaPintura.setSelected(false);
                vMeca.listaITV.setSelected(false);
    }
    
    void itv1(){
       if(vMeca.jCheckBox1.isSelected()){}else{ JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox2.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox3.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox4.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox5.isSelected() || vMeca.jCheckBox6.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox7.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox8.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox9.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox10.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox11.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox12.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox13.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox14.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox15.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox16.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox17.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox18.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox19.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox20.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox24.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox25.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox26.isSelected()){JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return;
       }else{vMeca.jTabbedPane1.setSelectedIndex(1);vMeca.btnEndCar_supITV1.setEnabled(false);vMeca.btnEndCar_supITV2.setEnabled(true);vMeca.jTabbedPane1.setEnabledAt(0, false);vMeca.jTabbedPane1.setEnabledAt(1, true); }
    }
    
    void itv2(){
       if(vMeca.jCheckBox27.isSelected()){}else{ JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox28.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox29.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox30.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox31.isSelected() || vMeca.jCheckBox6.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox32.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox33.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox37.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox38.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox39.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox40.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox41.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox42.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox43.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox44.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox45.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox46.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox47.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox48.isSelected()){JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return;
       }else{vMeca.jTabbedPane1.setSelectedIndex(2);vMeca.btnEndCar_supITV2.setEnabled(false);vMeca.btnEndCar_supITV3.setEnabled(true);vMeca.jTabbedPane1.setEnabledAt(1, false);vMeca.jTabbedPane1.setEnabledAt(2, true); }
    }
    
    void itv3(){
        
       if(vMeca.jCheckBox50.isSelected()){}else{ JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox51.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox52.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox53.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox54.isSelected() || vMeca.jCheckBox6.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox55.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox56.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox57.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox58.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox59.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox60.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox61.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox62.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox63.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox64.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox65.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox66.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox67.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox68.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox70.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox71.isSelected()){}else{ JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox72.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox73.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox74.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox75.isSelected() || vMeca.jCheckBox6.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox77.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox78.isSelected() || vMeca.jCheckBox69.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox79.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox80.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox81.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox82.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox83.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox84.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }
       if(vMeca.jCheckBox85.isSelected()){}else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return; }      
       if(vMeca.jCheckBox86.isSelected()){vMeca.jTabbedPane1.setSelectedIndex(1);vMeca.btnEndCar_supITV3.setEnabled(false);vMeca.btnEndCar_supITV.setEnabled(true);vMeca.jTabbedPane1.setEnabledAt(2, false); 
       }else{JOptionPane.showMessageDialog(vMeca, "Este coche necesita una visita al mecanico antes de su proxima inspección, Pida cita"); return;}
    
    }
}
