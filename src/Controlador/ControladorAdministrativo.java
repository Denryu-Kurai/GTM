package Controlador;

// @author Denryu Kurai Seishi

import Modelo.Consultas;
import Modelo.EncriptadorMD5;
import Vista.AdministrativoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ControladorAdministrativo implements ActionListener, MouseListener {
    
    AdministrativoView vAdmin;
    Consultas consulta = new Consultas();
    EncriptadorMD5 md5 = new EncriptadorMD5();
    boolean b; // Este atributo actuara de bandera para indicar a los procedimientos relacionados a lugares que realicen funciones adicionales.
    
    public ControladorAdministrativo (AdministrativoView vA) {
        
        this.vAdmin = vA;
        
        
    }
    
    public enum ActionMVC {
        
        // Menu
        __CerrarUsuario, __Salir, __CambiarContraseña,
        
        // Botones
        __SeccionUC, __SeccionServicios,
        __SeccionFacturas, __SeccionInformes,
        
        // Usuarios
        __BuscarUsuarios, __AñadirUsuario, __InsertarUsuario,
        __ModificarUsuario, __EliminarUsuario,
        
        // Coches
        __BuscarCoches, __AñadirCoche, __InsertarCoche,
        __ModificarCoche, __EliminarCoche, __NuevoLugar;
        
    }
    
    public void iniciar () {
        
        // Acciones del menu.
        vAdmin.btnCerrarUsuario.setActionCommand("__CerrarUsuario");
        vAdmin.btnCerrarUsuario.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Salir");
        vAdmin.btnSalir.addActionListener(this);
        
        // Botones del Administrativo.
        vAdmin.btnUC.setActionCommand("__SeccionUC");
        vAdmin.btnUC.addActionListener(this);
        vAdmin.btnFacturas.setActionCommand("__SeccionFacturas");
        vAdmin.btnFacturas.addActionListener(this);
        vAdmin.btnInformes.setActionCommand("__SeccionInformes");
        vAdmin.btnInformes.addActionListener(this);
        
        // Botones de gestion de usuarios.
        vAdmin.btnUBuscar.setActionCommand("__BuscarUsuarios");
        vAdmin.btnUBuscar.addActionListener(this);
        vAdmin.btnUAñadir.setActionCommand("__AñadirUsuario");
        vAdmin.btnUAñadir.addActionListener(this);
        vAdmin.btnUInsertar.setActionCommand("__InsertarUsuario");
        vAdmin.btnUInsertar.addActionListener(this);
        vAdmin.btnUModificar.setActionCommand("__ModificarUsuario");
        vAdmin.btnUModificar.addActionListener(this);
        vAdmin.btnUEliminar.setActionCommand("__EliminarUsuario");
        vAdmin.btnUEliminar.addActionListener(this);
        
        // Botones de gestion de coches.
        vAdmin.btnCBuscar.setActionCommand("__BuscarCoches");
        vAdmin.btnCBuscar.addActionListener(this);
        vAdmin.btnCAñadir.setActionCommand("__AñadirCoche");
        vAdmin.btnCAñadir.addActionListener(this);
        vAdmin.btnCInsertar.setActionCommand("__InsertarCoche");
        vAdmin.btnCInsertar.addActionListener(this);
        vAdmin.btnCModificar.setActionCommand("__ModificarCoche");
        vAdmin.btnCModificar.addActionListener(this);
        vAdmin.btnCEliminar.setActionCommand("__EliminarCoche");
        vAdmin.btnCEliminar.addActionListener(this);
        vAdmin.btnCLugar.setActionCommand("__NuevoLugar");
        vAdmin.btnCLugar.addActionListener(this);
        
        // Prepara las tablas.
        vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
        vAdmin.tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                
                vAdmin.txtUDNI.setFocusable(false);
                vAdmin.btnUInsertar.setVisible(false);
                vAdmin.btnUModificar.setVisible(true);
                vAdmin.btnUEliminar.setVisible(true);
                
                vAdmin.pnUsuario.setVisible(true);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnBlanco.setVisible(false);
                
                limpiar();
                
                int fila = vAdmin.tblUsuarios.getSelectedRow();
                String id = (String) vAdmin.tblUsuarios.getValueAt(fila, 0);
                String[] persona = consulta.getPersona(id);
                
                vAdmin.txtUDNI.setText(persona[0]);
                vAdmin.txtUUsuario.setText(persona[1]);
                vAdmin.txtUNombre.setText(persona[2]);
                vAdmin.txtUApellidos.setText(persona[3]);
                vAdmin.txtUDireccion.setText(persona[4]);
                vAdmin.txtUTelefono.setText(persona[5]);
                
                if (persona[6].equals("cliente")) {
                    
                    vAdmin.cbURol.setSelectedIndex(0);
                    
                } else if (persona[6].equals("mecanico")) {
                    
                    vAdmin.cbURol.setSelectedIndex(1);
                    
                } else {
                    
                    vAdmin.cbURol.setSelectedIndex(2);
                    
                }
                
                vAdmin.tblCoches.setModel(consulta.tablaCochesUsuarioAdministrativo(persona[0]));
                
            }
        });
        
        vAdmin.tblCoches.setModel(consulta.tablaCochesAdministrativo());
        vAdmin.tblCoches.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                
                vAdmin.txtCMatricula.setFocusable(false);
                vAdmin.txtCPropietario.setFocusable(true);
                vAdmin.btnCInsertar.setVisible(false);
                vAdmin.btnCModificar.setVisible(true);
                vAdmin.btnCEliminar.setVisible(true);
                
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(true);
                vAdmin.pnBlanco.setVisible(false);
                
                limpiar();
                
                b = false;
                
                int fila = vAdmin.tblCoches.getSelectedRow();
                String id = (String) vAdmin.tblCoches.getValueAt(fila, 0);
                String[] coche = consulta.getCoche(id);
                
                vAdmin.txtCMatricula.setText(coche[0]);
                vAdmin.txtCPropietario.setText(coche[1]);
                vAdmin.txtCMarca.setText(coche[2]);
                vAdmin.txtCModelo.setText(coche[3]);
                vAdmin.txtCColor.setText(coche[4]);
                
                
                if (coche[5].equals("2")) {
                    
                    vAdmin.cbCPlazas.setSelectedIndex(0);
                    
                } else if (coche[5].equals("5")) {
                    
                    vAdmin.cbCPlazas.setSelectedIndex(1);
                    
                } else if (coche[5].equals("7")) {
                    
                    vAdmin.cbCPlazas.setSelectedIndex(2);
                    
                } else {
                    
                    vAdmin.cbCPlazas.setSelectedIndex(3);
                    
                }
                
                if (coche[6].equals("2")) {
                    
                    vAdmin.cbCEjes.setSelectedIndex(0);
                    
                } else {
                    
                    vAdmin.cbCEjes.setSelectedIndex(1);
                    
                }
                
                if (coche[7].equals("3")) {
                    
                    vAdmin.cbCPuertas.setSelectedIndex(0);
                    
                } else if (coche[7].equals("5")) {
                    
                    vAdmin.cbCPuertas.setSelectedIndex(1);
                    
                } else {
                    
                    vAdmin.cbCPuertas.setSelectedIndex(2);
                    
                }
                
                int c = consulta.contarLugarCoche(id);
                
                if (c == 0) {
                    
                    vAdmin.ckbTaller.setEnabled(false);
                    vAdmin.ckbITV.setEnabled(false);
                    vAdmin.ckbPintura.setEnabled(false);
                    vAdmin.ckbEspera.setEnabled(false);
                    
                    vAdmin.btnCLugar.setEnabled(true);
                    
                } else {
                    
                    vAdmin.ckbTaller.setEnabled(true);
                    vAdmin.ckbITV.setEnabled(true);
                    vAdmin.ckbPintura.setEnabled(true);
                    vAdmin.ckbEspera.setEnabled(true);
                    
                    vAdmin.btnCLugar.setEnabled(false);
                    
                    coche = consulta.getLugar(id);
                    
                    if (coche[0].equals("1")) vAdmin.ckbTaller.setSelected(true);
                    if (coche[1].equals("1")) vAdmin.ckbITV.setSelected(true);
                    if (coche[2].equals("1")) vAdmin.ckbPintura.setSelected(true);
                    if (coche[3].equals("1")) vAdmin.ckbEspera.setSelected(true);
                    
                }
                
            }
        });
        
        // Prepara la vista Administrativa.
        vAdmin.pnUC.setVisible(true);
        vAdmin.pnBlanco.setVisible(true);
        vAdmin.pnUsuario.setVisible(false);
        vAdmin.pnCoche.setVisible(false);
        vAdmin.pnFacturas.setVisible(false);
        vAdmin.pnInformes.setVisible(false);
        
        vAdmin.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String dni, nombre, apellidos, direccion, usuario, contraseña, rol;
        String matricula, propietario, marca, modelo, color;
        int telefono, fila, c;
        int puertas, ejes, plazas, taller, itv, pintura, espera;
        boolean r;
        
        switch (ActionMVC.valueOf(e.getActionCommand())) {
            
            /**
             * *********************MENU**************************
             */
            case __CerrarUsuario:
                this.vAdmin.setVisible(false);
                
                ControladorLogin cntrl = ControladorLogin.getInstance();
                cntrl.cargar();
                break;
                
            case __Salir:
                System.exit(0);
                break;
                
            case __CambiarContraseña:
                break;
            
            /**
             * *************BOTONES ADMINISTRATIVO******************
             */
            case __SeccionUC:
                vAdmin.pnUC.setVisible(true);
                vAdmin.pnBlanco.setVisible(true);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionServicios:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionFacturas:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnFacturas.setVisible(true);
                vAdmin.pnInformes.setVisible(false);
                break;
                
            case __SeccionInformes:
                vAdmin.pnUC.setVisible(false);
                vAdmin.pnUsuario.setVisible(false);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnFacturas.setVisible(false);
                vAdmin.pnInformes.setVisible(true);
                break;
                
            /**
             * *************USUARIOS******************
             */
            case __BuscarUsuarios:
                vAdmin.tblUsuarios.setModel(consulta.buscarUsuariosAdministrativo(vAdmin.txtUBuscar.getText()));
                break;
                
            case __AñadirUsuario:
                limpiar();
                
                vAdmin.txtUDNI.setFocusable(true);
                vAdmin.btnUInsertar.setVisible(true);
                vAdmin.btnUModificar.setVisible(false);
                vAdmin.btnUEliminar.setVisible(false);
                
                vAdmin.pnUsuario.setVisible(true);
                vAdmin.pnCoche.setVisible(false);
                vAdmin.pnBlanco.setVisible(false);
                break;
                
            case __InsertarUsuario:
                c = 0;
                r = false;
                
                if (vAdmin.txtUDNI.getText().equals("         ")) c++;
                if (vAdmin.txtUUsuario.getText().equals("")) c++;
                if (vAdmin.txtUNombre.getText().equals("")) c++;
                if (vAdmin.txtUApellidos.getText().equals("")) c++;
                if (vAdmin.txtUDireccion.getText().equals("")) c++;
                if (vAdmin.txtUTelefono.getText().equals("         ")) c++;
                
                if (c > 0) {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Rellena todos los campos.");
                    
                } else {
                    
                    dni = vAdmin.txtUDNI.getText();
                    usuario = vAdmin.txtUUsuario.getText();
                    nombre = vAdmin.txtUNombre.getText();
                    apellidos = vAdmin.txtUApellidos.getText();
                    direccion = vAdmin.txtUDireccion.getText();
                    telefono = Integer.parseInt(vAdmin.txtUTelefono.getText());
                        
                    r = consulta.insertarUsuario(dni, nombre, apellidos, direccion, telefono, (String) vAdmin.cbURol.getSelectedItem(), usuario, md5.encriptarEnMD5(dni));
                    
                    if (r == true) {
                        
                        JOptionPane.showMessageDialog(vAdmin, "Error al insertar el usuario.");
                        
                    } else {
                        
                        vAdmin.tblCoches.setModel(consulta.tablaCochesAdministrativo());
                    
                        vAdmin.pnUsuario.setVisible(false);
                        vAdmin.pnCoche.setVisible(false);
                        vAdmin.pnBlanco.setVisible(true);
                        
                    }
                    
                }
                break;
                
            case __ModificarUsuario:
                c = 0;
                r = false;
                
                if (vAdmin.txtUUsuario.getText().equals("")) c++;
                if (vAdmin.txtUNombre.getText().equals("")) c++;
                if (vAdmin.txtUApellidos.getText().equals("")) c++;
                if (vAdmin.txtUDireccion.getText().equals("")) c++;
                if (vAdmin.txtUTelefono.getText().equals("         ")) c++;
                
                if (c > 0) {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Rellena todos los campos.");
                    
                } else {
                    
                    dni = vAdmin.txtUDNI.getText();
                    usuario = vAdmin.txtUUsuario.getText();
                    nombre = vAdmin.txtUNombre.getText();
                    apellidos = vAdmin.txtUApellidos.getText();
                    direccion = vAdmin.txtUDireccion.getText();
                    telefono = Integer.parseInt(vAdmin.txtUTelefono.getText());
                        
                    r = consulta.modificarUsuario(dni, nombre, apellidos, direccion, telefono, (String) vAdmin.cbURol.getSelectedItem(), usuario);
                    
                    if (r == true) {
                        
                        JOptionPane.showMessageDialog(vAdmin, "Error al modificar el usuario.");
                        
                    } else {
                        
                        vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
                    
                        vAdmin.pnUsuario.setVisible(false);
                        vAdmin.pnCoche.setVisible(false);
                        vAdmin.pnBlanco.setVisible(true);
                        
                    }
                    
                }
                break;
                
            case __EliminarUsuario:
                fila = vAdmin.tblUsuarios.getSelectedRow();
                dni = (String) vAdmin.tblUsuarios.getValueAt(fila, 0);
                c = consulta.contarCochesUsuario(dni);
                
                if (c == 0) {
                    
                    consulta.eliminarUsuario(dni);
                    
                    vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
                    
                    vAdmin.pnUsuario.setVisible(false);
                    vAdmin.pnCoche.setVisible(false);
                    vAdmin.pnBlanco.setVisible(true);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Ya hay coches almacenados con este usuario.");
                    
                }
                break;
                
            /**
             * *************COCHES******************
             */
            case __BuscarCoches:
                vAdmin.tblCoches.setModel(consulta.buscarCochesAdministrativo(vAdmin.txtCBuscar.getText()));
                break;
                
            case __AñadirCoche:
                fila = this.vAdmin.tblUsuarios.getSelectedRow();
                
                if (fila == -1) {
                    
                    JOptionPane.showMessageDialog(null, "Tiene que seleccionar un usuario.");
                    
                } else {
                    
                    limpiar();
                
                    vAdmin.txtCMatricula.setFocusable(true);
                    vAdmin.txtCPropietario.setFocusable(false);
                    vAdmin.btnCInsertar.setVisible(true);
                    vAdmin.btnCModificar.setVisible(false);
                    vAdmin.btnCEliminar.setVisible(false);
                
                    vAdmin.pnUsuario.setVisible(false);
                    vAdmin.pnCoche.setVisible(true);
                    vAdmin.pnBlanco.setVisible(false);
                    
                    vAdmin.txtCPropietario.setText((String) vAdmin.tblUsuarios.getValueAt(fila, 0));
                    
                    vAdmin.ckbTaller.setEnabled(true);
                    vAdmin.ckbITV.setEnabled(true);
                    vAdmin.ckbPintura.setEnabled(true);
                    vAdmin.ckbEspera.setSelected(true);
                   
                    vAdmin.btnCLugar.setEnabled(false);
                    
                }
                break;
                
            case __InsertarCoche:
                c = 0;
                r = false;
                
                if (vAdmin.txtCMatricula.getText().equals("         ")) c++;
                if (vAdmin.txtCPropietario.getText().equals("         ")) c++;
                if (vAdmin.txtCMarca.getText().equals("")) c++;
                if (vAdmin.txtCModelo.getText().equals("")) c++;
                if (vAdmin.txtCColor.getText().equals("")) c++;
                if (!vAdmin.ckbTaller.isSelected() && !vAdmin.ckbITV.isSelected() && !vAdmin.ckbPintura.isSelected()) c++;
                
                if (c > 0) {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Rellena todos los campos.");
                    
                } else {
                    
                    matricula = vAdmin.txtCMatricula.getText();
                    propietario = vAdmin.txtCPropietario.getText();
                    marca = vAdmin.txtCMarca.getText();
                    modelo = vAdmin.txtCModelo.getText();
                    color = vAdmin.txtCColor.getText();
                    plazas = Integer.parseInt((String) vAdmin.cbCPlazas.getSelectedItem());
                    ejes = Integer.parseInt((String) vAdmin.cbCEjes.getSelectedItem());
                    puertas = Integer.parseInt((String) vAdmin.cbCPuertas.getSelectedItem());
                    
                    if (vAdmin.ckbTaller.isSelected())
                        taller = 1;
                    else
                        taller = 0;
                    
                    if (vAdmin.ckbITV.isSelected())
                        itv = 1;
                    else
                        itv = 0;
                    
                    if (vAdmin.ckbPintura.isSelected())
                        pintura = 1;
                    else
                        pintura = 0;
                        
                    r = consulta.insertarCoche(matricula, marca, modelo, color, plazas, ejes, puertas, propietario, taller, itv, pintura);
                    
                    if (r == true) {
                        
                        JOptionPane.showMessageDialog(vAdmin, "Error al insertar el usuario.");
                        
                    } else {
                        
                        vAdmin.tblCoches.setModel(consulta.tablaCochesAdministrativo());
                    
                        vAdmin.pnUsuario.setVisible(false);
                        vAdmin.pnCoche.setVisible(false);
                        vAdmin.pnBlanco.setVisible(true);
                        
                    }
                    
                }
                break;
                
            case __ModificarCoche:
                c = 0;
                r = false;
                
                if (vAdmin.txtCMatricula.getText().equals("         ")) c++;
                if (vAdmin.txtCPropietario.getText().equals("         ")) c++;
                if (vAdmin.txtCMarca.getText().equals("")) c++;
                if (vAdmin.txtCModelo.getText().equals("")) c++;
                if (vAdmin.txtCColor.getText().equals("")) c++;
                if (!vAdmin.ckbTaller.isSelected() && !vAdmin.ckbITV.isSelected() && !vAdmin.ckbPintura.isSelected() && b == true) c++;
                
                if (c > 0) {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Rellena todos los campos.");
                    
                } else {
                    
                    matricula = vAdmin.txtCMatricula.getText();
                    propietario = vAdmin.txtCPropietario.getText();
                    marca = vAdmin.txtCMarca.getText();
                    modelo = vAdmin.txtCModelo.getText();
                    color = vAdmin.txtCColor.getText();
                    plazas = Integer.parseInt((String) vAdmin.cbCPlazas.getSelectedItem());
                    ejes = Integer.parseInt((String) vAdmin.cbCEjes.getSelectedItem());
                    puertas = Integer.parseInt((String) vAdmin.cbCPuertas.getSelectedItem());
                        
                    r = consulta.modificarCoche(matricula, marca, modelo, color, plazas, ejes, puertas, propietario);
                    
                    if (r == true) {
                        
                        JOptionPane.showMessageDialog(vAdmin, "Error al modificar el usuario.");
                        
                    } else {
                        
                        if (b == true) {
                            
                            if (vAdmin.ckbTaller.isSelected())
                                taller = 1;
                            else
                                taller = 0;
                    
                            if (vAdmin.ckbITV.isSelected())
                                itv = 1;
                            else
                                itv = 0;
                    
                            if (vAdmin.ckbPintura.isSelected())
                                pintura = 1;
                            else
                                pintura = 0;
                            
                            consulta.insertarLugar(matricula, taller, itv, pintura);
                            
                        } else {
                            
                            if (vAdmin.ckbTaller.isSelected())
                                taller = 1;
                            else
                                taller = 0;
                    
                            if (vAdmin.ckbITV.isSelected())
                                itv = 1;
                            else
                                itv = 0;
                    
                            if (vAdmin.ckbPintura.isSelected())
                                pintura = 1;
                            else
                                pintura = 0;
                            
                            if (vAdmin.ckbEspera.isSelected())
                                espera = 1;
                            else
                                espera = 0;
                            
                            consulta.modificarLugar(matricula, taller, itv, pintura, espera);
                            
                        }
                        
                        vAdmin.tblUsuarios.setModel(consulta.tablaUsuariosAdministrativo());
                    
                        vAdmin.pnUsuario.setVisible(false);
                        vAdmin.pnCoche.setVisible(false);
                        vAdmin.pnBlanco.setVisible(true);
                        
                    }
                    
                }
                break;
                
            case __EliminarCoche:
                fila = vAdmin.tblCoches.getSelectedRow();
                matricula = (String) vAdmin.tblCoches.getValueAt(fila, 0);
                c = consulta.contarHistorialCoche(matricula);
                
                if (c == 0) {
                    
                    consulta.eliminarLugar(matricula);
                    consulta.eliminarCoche(matricula);
                    
                    vAdmin.tblCoches.setModel(consulta.tablaCochesAdministrativo());
                    
                    vAdmin.pnUsuario.setVisible(false);
                    vAdmin.pnCoche.setVisible(false);
                    vAdmin.pnBlanco.setVisible(true);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(vAdmin, "Error. Ya hay un historial almacenado con este coche.");
                    
                }
                break;
                
            case __NuevoLugar:
                b = true;
                
                vAdmin.ckbTaller.setEnabled(true);
                vAdmin.ckbITV.setEnabled(true);
                vAdmin.ckbPintura.setEnabled(true);
                vAdmin.ckbEspera.setSelected(true);
                   
                vAdmin.btnCLugar.setEnabled(false);
                break;
            
        }
        
    }
    
    public void limpiar () {
        
        vAdmin.txtUDNI.setText("");
        vAdmin.txtUUsuario.setText("");
        vAdmin.txtUNombre.setText("");
        vAdmin.txtUApellidos.setText("");
        vAdmin.txtUDireccion.setText("");
        vAdmin.txtUTelefono.setText("");
        vAdmin.cbURol.setSelectedIndex(0);
        
        vAdmin.txtCMatricula.setText("");
        vAdmin.txtCPropietario.setText("");
        vAdmin.txtCMarca.setText("");
        vAdmin.txtCModelo.setText("");
        vAdmin.txtCColor.setText("");
        vAdmin.cbCPlazas.setSelectedIndex(0);
        vAdmin.cbCEjes.setSelectedIndex(0);
        vAdmin.cbCPuertas.setSelectedIndex(0);
        vAdmin.ckbTaller.setSelected(false);
        vAdmin.ckbITV.setSelected(false);
        vAdmin.ckbPintura.setSelected(false);
        vAdmin.ckbEspera.setSelected(false);
        
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