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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controlador implements ActionListener, MouseListener {

    LoginView vistaLogin;
    AdministrativoView vistaAdministrativo;
    MecanicoView vistaMecanico;
    Consultas consulta = new Consultas();
    private Date fecha = new Date();
    private String user;
    private String passw;

    public Controlador(LoginView vL, AdministrativoView vA, MecanicoView vM) {

        this.vistaLogin = vL;
        this.vistaAdministrativo = vA;
        this.vistaMecanico = vM;

    }

    public enum AccionMVC {

        __Conectar,
        __Registrarse,
        __ACerrarUsuario,
        __ASalir,
        __SubirImagen,
        //pendiente de revision
        __Listame, __Presupuesteame, __Tallerizame, __Pintame, __ITVme;
    }

    public void iniciar() {

        //Botones del Login
        vistaLogin.btnConectar.setActionCommand("__Conectar");
        vistaLogin.btnConectar.addActionListener(this);
        vistaLogin.btnRegistrarse.setActionCommand("__Registrarse");
        vistaLogin.btnRegistrarse.addActionListener(this);

        //Botones del Administrativo
        vistaAdministrativo.btnCerrarUsuario.setActionCommand("__ACerrarUsuario");
        vistaAdministrativo.btnCerrarUsuario.addActionListener(this);
        vistaAdministrativo.btnSalir.setActionCommand("__ASalir");
        vistaAdministrativo.btnSalir.addActionListener(this);

        //Botones del Mecanico
        vistaMecanico.btnListCar.setActionCommand("__Listame");
        vistaMecanico.btnListCar.addActionListener(this);
        vistaMecanico.btnItv.setActionCommand("__Presupuesteame");
        vistaMecanico.btnItv.addActionListener(this);
        vistaMecanico.btnPresup.setActionCommand("__Tallerizame");
        vistaMecanico.btnPresup.addActionListener(this);
        vistaMecanico.btnTaller.setActionCommand("__Pintame");
        vistaMecanico.btnTaller.addActionListener(this);
        vistaMecanico.btnPintura.setActionCommand("__ITVme");
        vistaMecanico.btnPintura.addActionListener(this);

        //Ponemos los paneles en false, los activamos con los botones
        //Layered 1
        vistaMecanico.L1_panelLista.setVisible(true);
        vistaMecanico.L1_panelPresupuesto.setVisible(false);
        vistaMecanico.L1_panelTaller.setVisible(false);
        vistaMecanico.L1_panelPintura.setVisible(false);
        vistaMecanico.L1_panelITV.setVisible(false);
        //Layered 2
        vistaMecanico.L2_panelLista.setVisible(true);
        vistaMecanico.L2_panelPresupuesto.setVisible(false);
        vistaMecanico.L2_panelTaller.setVisible(false);
        vistaMecanico.L2_panelPintura.setVisible(false);
        vistaMecanico.L2_panelITV.setVisible(false);
        try {
            //Tablas
            vistaMecanico.tablaLista.setModel(consulta.tablaCoches());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Lanzamos el Login
        vistaLogin.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case __Conectar:
                user = vistaLogin.txtUsuario.getText();
                passw = vistaLogin.txtContraseña.toString();

                if (consulta.getLogin(user, passw) == true) {
                    switch (consulta.getRol(user)) {
                        case "mecanico":
                            vistaLogin.setVisible(false);
                            vistaMecanico.setVisible(true);
                            break;
                        case "administra":
                            vistaLogin.setVisible(false);
                            vistaAdministrativo.setVisible(true);
                            break;
                        default:
                            System.out.println("Error al comparar los roles");
                            break;
                    }
                }
//
//                if (this.user.equals("Administrativo") ) {
//                    this.vistaLogin.setVisible(false);
//                    this.vistaAdministrativo.setVisible(true);
//                } else if (this.user.equals("Mecanico")) {
//                    vistaLogin.setVisible(false);
//                    vistaMecanico.setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(this.vistaLogin, "Error al conectar. \n"
//                            + "Usuarios: \n"
//                            + "- Administrativo \n"
//                            + "- Mecanico \n"
//                            + "Sin tocar contraseña.");
//                }
                break;

            case __Registrarse:
                break;

            case __ACerrarUsuario:
                this.user = null;
                this.vistaLogin.txtUsuario.setText("");
                this.vistaLogin.txtContraseña.setText("");
                this.vistaAdministrativo.setVisible(false);
                this.vistaLogin.setVisible(true);
                break;

            case __Listame:
                //Paneles Layered 1
                vistaMecanico.L1_panelLista.setVisible(true);
                vistaMecanico.L1_panelPresupuesto.setVisible(false);
                vistaMecanico.L1_panelTaller.setVisible(false);
                vistaMecanico.L1_panelPintura.setVisible(false);
                vistaMecanico.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vistaMecanico.L2_panelLista.setVisible(true);
                vistaMecanico.L2_panelPresupuesto.setVisible(false);
                vistaMecanico.L2_panelTaller.setVisible(false);
                vistaMecanico.L2_panelPintura.setVisible(false);
                vistaMecanico.L2_panelITV.setVisible(false);
                break;

            case __SubirImagen:
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(vistaMecanico);
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    System.out.println("You chose to open this file: "
                            + chooser.getSelectedFile().getName());

                }
                break;

            case __Presupuesteame:
                //Paneles Layered 1
                vistaMecanico.L1_panelLista.setVisible(false);
                vistaMecanico.L1_panelPresupuesto.setVisible(true);
                vistaMecanico.L1_panelTaller.setVisible(false);
                vistaMecanico.L1_panelPintura.setVisible(false);
                vistaMecanico.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vistaMecanico.L2_panelLista.setVisible(false);
                vistaMecanico.L2_panelPresupuesto.setVisible(true);
                vistaMecanico.L2_panelTaller.setVisible(false);
                vistaMecanico.L2_panelPintura.setVisible(false);
                vistaMecanico.L2_panelITV.setVisible(false);
                break;

            case __Tallerizame:
                //Paneles Layered 1
                vistaMecanico.L1_panelLista.setVisible(false);
                vistaMecanico.L1_panelPresupuesto.setVisible(false);
                vistaMecanico.L1_panelTaller.setVisible(true);
                vistaMecanico.L1_panelPintura.setVisible(false);
                vistaMecanico.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vistaMecanico.L2_panelLista.setVisible(false);
                vistaMecanico.L2_panelPresupuesto.setVisible(false);
                vistaMecanico.L2_panelTaller.setVisible(true);
                vistaMecanico.L2_panelPintura.setVisible(false);
                vistaMecanico.L2_panelITV.setVisible(false);
                break;

            case __Pintame:
                //Paneles Layered 1
                vistaMecanico.L1_panelLista.setVisible(false);
                vistaMecanico.L1_panelPresupuesto.setVisible(false);
                vistaMecanico.L1_panelTaller.setVisible(false);
                vistaMecanico.L1_panelPintura.setVisible(true);
                vistaMecanico.L1_panelITV.setVisible(false);
                //Paneles Layered 2
                vistaMecanico.L2_panelLista.setVisible(false);
                vistaMecanico.L2_panelPresupuesto.setVisible(false);
                vistaMecanico.L2_panelTaller.setVisible(false);
                vistaMecanico.L2_panelPintura.setVisible(true);
                vistaMecanico.L2_panelITV.setVisible(false);
                break;

            case __ITVme:
                //Paneles Layered 1
                vistaMecanico.L1_panelLista.setVisible(false);
                vistaMecanico.L1_panelPresupuesto.setVisible(false);
                vistaMecanico.L1_panelTaller.setVisible(false);
                vistaMecanico.L1_panelPintura.setVisible(false);
                vistaMecanico.L1_panelITV.setVisible(true);
                //Paneles Layered 2
                vistaMecanico.L2_panelLista.setVisible(false);
                vistaMecanico.L2_panelPresupuesto.setVisible(false);
                vistaMecanico.L2_panelTaller.setVisible(false);
                vistaMecanico.L2_panelPintura.setVisible(false);
                vistaMecanico.L2_panelITV.setVisible(true);
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
    public void mousePressed(MouseEvent e) {
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
