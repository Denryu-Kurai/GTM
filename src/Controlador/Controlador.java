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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controlador implements ActionListener, MouseListener {

    LoginView vLogin;
    AdministrativoView vAdmin;
    MecanicoView vMeca;
    Consultas consulta = new Consultas();
    private Date fecha = new Date();
    private String user;
    private String passw;

    public Controlador(LoginView vL, AdministrativoView vA, MecanicoView vM) {

        this.vLogin = vL;
        this.vAdmin = vA;
        this.vMeca = vM;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum AccionMVC {

        //Login
        __Conectar, __Registrarse,
        //Menu
        __ACerrarUsuario,
        __ASalir,
        //Administrativo
        __NewCliente, __ModCliente,
        __NewCoche, __ModCoche,
        __Factura, __Informes, __Servicios,
        //JDialog Administrativo
        __JdNcliAcepta, __JdNcliCancela, __JdNcoConfirma, __JdNcoCancelar,
        //Mecanico
        __Listame, __Presupuesteame, __Tallerizame, __Pintame, __ITVme, __SubirImagen;
    }

    public void iniciar() {

//Botones del Login
        vLogin.btnConectar.setActionCommand("__Conectar");
        vLogin.btnConectar.addActionListener(this);
        vLogin.btnRegistrarse.setActionCommand("__Registrarse");
        vLogin.btnRegistrarse.addActionListener(this);

//Acciones del menu
        vAdmin.btnCerrarUsuario.setActionCommand("__ACerrarUsuario");
        vAdmin.btnCerrarUsuario.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ASalir");
        vAdmin.btnSalir.addActionListener(this);

//Botones del Administrativo
        vAdmin.btnSalir.setActionCommand("__NewCliente");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ModCliente");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__NewCoche");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__ModCoche");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Factura");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Informes");
        vAdmin.btnSalir.addActionListener(this);
        vAdmin.btnSalir.setActionCommand("__Servicios");
        vAdmin.btnSalir.addActionListener(this);

//Botones del JDialogNewCliente
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcliAcepta");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcliCancela");
        vAdmin.JDNCbtnAceptar.addActionListener(this);

        //Botones del JDialogNewCoche
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcoConfirma");
        vAdmin.JDNCbtnAceptar.addActionListener(this);
        vAdmin.JDNCbtnAceptar.setActionCommand("__JdNcoCancelar");
        vAdmin.JDNCbtnAceptar.addActionListener(this);

//Botones del Mecanico
        vMeca.btnListCar.setActionCommand("__Listame");
        vMeca.btnListCar.addActionListener(this);
        vMeca.btnItv.setActionCommand("__Presupuesteame");
        vMeca.btnItv.addActionListener(this);
        vMeca.btnPresup.setActionCommand("__Tallerizame");
        vMeca.btnPresup.addActionListener(this);
        vMeca.btnTaller.setActionCommand("__Pintame");
        vMeca.btnTaller.addActionListener(this);
        vMeca.btnPintura.setActionCommand("__ITVme");
        vMeca.btnPintura.addActionListener(this);

        //Lanzamos el Login
        vLogin.setVisible(true);

        
            vMeca.tablaLista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int i=vMeca.tablaLista.getSelectedRow();
                String o = vMeca.tablaLista.getValueAt(i,0).toString();               
                ArrayList a = consulta.datosLista(consulta.getDni(o));
                vMeca.listaModelo.setText(a.get(0).toString());
                vMeca.listaMarca.setText(a.get(1).toString());
                vMeca.listaPropietario.setText(a.get(2).toString());
                vMeca.listaTelefono.setText(a.get(3).toString());
                vMeca.listaMatricul.setText(a.get(4).toString());
                ArrayList b = consulta.datosLugar(o);
                vMeca.listaTaller.setSelected((Boolean)b.get(0));
                vMeca.listaPintura.setSelected((Boolean)b.get(1));
                vMeca.listaITV.setSelected((Boolean)b.get(2));
            }
            });
        
    }
            
                


        
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (AccionMVC.valueOf(e.getActionCommand())) {
            /**
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
                            vMeca.setVisible(true);
                            //Ponemos los paneles en false, los activamos con los botones
                            //Layered 1
                            vMeca.L1_panelLista.setVisible(true);
                            vMeca.L1_panelPresupuesto.setVisible(false);
                            vMeca.L1_panelTaller.setVisible(false);
                            vMeca.L1_panelPintura.setVisible(false);
                            vMeca.L1_panelITV.setVisible(false);
                            //Layered 2
                            vMeca.L2_panelLista.setVisible(true);
                            vMeca.L2_panelPresupuesto.setVisible(false);
                            vMeca.L2_panelTaller.setVisible(false);
                            vMeca.L2_panelPintura.setVisible(false);
                            vMeca.L2_panelITV.setVisible(false);
                            try {
                                //Tablas
                                vMeca.tablaLista.setModel(consulta.tablaCoches());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            break;
                        case "administra":
                            vLogin.setVisible(false);
                            vAdmin.setVisible(true);
                            break;
                        default:
                            System.out.println("Error al comparar los roles");
                            break;
                    }
                }
                break;
            case __Registrarse:
                break;
            /**
             * ********ADMINISTRATIVO*********
             */

            case __NewCliente:
                break;
            case __ModCliente:
                break;
            case __NewCoche:
                break;
            case __ModCoche:
                break;

            case __Servicios:
                break;
            case __Informes:
                break;
            case __Factura:
                break;
            //dialogs
            case __JdNcliAcepta:
                break;
            case __JdNcliCancela:
                break;
            case __JdNcoConfirma:
                break;
            case __JdNcoCancelar:
                break;
            /**
             * ************MECANICO*****************
             */
            case __Listame:
                //Paneles Layered 1
                vMeca.L1_panelLista.setVisible(true);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vMeca.L2_panelLista.setVisible(true);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __SubirImagen:
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(vMeca);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: "
                            + chooser.getSelectedFile().getName());

                }
                break;

            case __Presupuesteame:
                //Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(true);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(true);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Tallerizame:
                //Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(true);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(true);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __Pintame:
                //Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(true);
                vMeca.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(true);
                vMeca.L2_panelITV.setVisible(false);
                break;

            case __ITVme:
                //Paneles Layered 1
                vMeca.L1_panelLista.setVisible(false);
                vMeca.L1_panelPresupuesto.setVisible(false);
                vMeca.L1_panelTaller.setVisible(false);
                vMeca.L1_panelPintura.setVisible(false);
                vMeca.L1_panelITV.setVisible(true);
                //Paneles Layered 2
                vMeca.L2_panelLista.setVisible(false);
                vMeca.L2_panelPresupuesto.setVisible(false);
                vMeca.L2_panelTaller.setVisible(false);
                vMeca.L2_panelPintura.setVisible(false);
                vMeca.L2_panelITV.setVisible(true);
                break;
            /**
             * *********************MENU**************************
             */
            case __ACerrarUsuario:
                this.user = null;
                this.vLogin.txtUsuario.setText("");
                this.vLogin.txtContraseña.setText("");
                this.vAdmin.setVisible(false);
                this.vLogin.setVisible(true);
                break;
            case __ASalir:
                System.exit(0);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }



    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
