package Controlador;

import Modelo.Entidades.Personal;
import DAOS.PersonalDAO;
import Vistas.JefeTaller;
import Vistas.Login;
import Vistas.VistaConductor;
import Vistas.VistaMecanico1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControlPrincipal implements ActionListener {

    private Login login;
    private JefeTaller jefeTaller;
    private VistaMecanico1 mecanico;
    private VistaConductor conductor;
    private ControladorMecanico controladorMecanico;
    private ControladorLogin controladorLogin;
    private ControladorBUS controladorBUS;
    private ControladorPersonal controladorPersonal;
    private ControladorRegistroInsi controladorInforme;
    private ControladorConductor controladorConductor;

    public ControlPrincipal() {
        login = new Login();
        jefeTaller = new JefeTaller();
        mecanico = new VistaMecanico1();
        conductor = new VistaConductor();
        controladorMecanico = new ControladorMecanico(mecanico);
        controladorLogin = new ControladorLogin(login);
        controladorBUS = new ControladorBUS(jefeTaller);
        controladorPersonal = new ControladorPersonal(jefeTaller);
        controladorInforme = new ControladorRegistroInsi(jefeTaller);
        controladorConductor = new ControladorConductor(conductor);
        login.bntIniciarSession.addActionListener(this);

        jefeTaller.btnCerrar.addActionListener(this);
        mecanico.btnCerrar.addActionListener(this);
        conductor.cerrarses.addActionListener(this);
        //modonoche
        jefeTaller.bntmodonoche.addActionListener(this);
        //jefeTaller.setVisible(false);
        //mecanico.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.bntIniciarSession) {
            if (autenticarUsuario()) {
                login.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(login, "Credenciales inválidas");
            }
        }
        if (e.getSource() == jefeTaller.btnCerrar) {
            int respuesta = JOptionPane.showConfirmDialog(jefeTaller, "Estas seguro de que desea cerrar Sesión", "Confirmar Cierre", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                login.setVisible(true);
                login.setLocationRelativeTo(null);
                jefeTaller.setVisible(false);
            }
        }

        if (e.getSource() == mecanico.btnCerrar) {
            int respuesta = JOptionPane.showConfirmDialog(mecanico, "¿Estás seguro de que deseas cerrar Sesión?", "Confirmar Cierre", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                mecanico.setVisible(false);
                login.setVisible(true);
                login.setLocationRelativeTo(null);
            }
        }
        if (e.getSource() == conductor.cerrarses) {
            int respuesta = JOptionPane.showConfirmDialog(conductor, "¿Estás seguro de que deseas cerrar Sesión?", "Confirmar Cierre", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                conductor.setVisible(false);
                login.setVisible(true);
                login.setLocationRelativeTo(null);
            }
        }
        //Codigo para el modo Noche En JEFE DE TALLER
        if (e.getSource() == jefeTaller.bntmodonoche) {
            if (jefeTaller.bntmodonoche.isSelected()) {
                jefeTaller.PanelEncabezado.setBackground(Color.BLACK);
                jefeTaller.jPanelBtns.setBackground(Color.BLACK);
                jefeTaller.panelUsuario.setBackground(Color.BLACK);
                jefeTaller.jPaPersonal.setBackground(new Color(102, 102, 102));
                jefeTaller.btnCerrar.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelbus.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelRepots.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanManteni.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelHistorial.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelDepersonales.setBackground(new Color(102, 102, 102));
                jefeTaller.jPaneDeAgregarPersonal.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelListarBuses.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelAgregarBuses.setBackground(new Color(102, 102, 102));
                jefeTaller.jPaneInformes.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelMantenimiento.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelRepoteConduc.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelReporteMecanico.setBackground(new Color(102, 102, 102));
                jefeTaller.jPanelhistorial.setBackground(new Color(102, 102, 102));

            } else {
                jefeTaller.PanelEncabezado.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanelBtns.setBackground(new Color(204, 204, 255));
                jefeTaller.panelUsuario.setBackground(new Color(204, 204, 255));
                jefeTaller.jPaPersonal.setBackground(new Color(204, 204, 255));
                jefeTaller.btnCerrar.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanelbus.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanelRepots.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanManteni.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanelHistorial.setBackground(new Color(204, 204, 255));
                jefeTaller.jPanelDepersonales.setBackground(Color.WHITE);
                jefeTaller.jPaneDeAgregarPersonal.setBackground(Color.white);
                jefeTaller.jPanelListarBuses.setBackground(Color.white);
                jefeTaller.jPanelAgregarBuses.setBackground(Color.WHITE);
                jefeTaller.jPaneInformes.setBackground(Color.WHITE);
                jefeTaller.jPanelRepoteConduc.setBackground(Color.WHITE);
                jefeTaller.jPanelReporteMecanico.setBackground(Color.WHITE);
                jefeTaller.jPanelhistorial.setBackground(Color.WHITE);
                jefeTaller.jPanelMantenimiento.setBackground(Color.WHITE);

            }

        }
    }

    private boolean autenticarUsuario() {
        String usuario = login.txtNombreUsuario.getText();
        String contrasenia = new String(login.txtContrasena.getPassword());

        PersonalDAO personalDAO = new PersonalDAO();
        Personal personal = personalDAO.obtenerPersonalPorUsuarioContraseña(usuario, contrasenia);

        if (personal != null) {
            // Credenciales válidas
            return true;
        } else {
            // Credenciales inválidas
            return false;
        }

        // Simulación de autenticación exitosa
    }

    void abrirVentanaUsuario(String usuario) {
        if (usuario.startsWith("mec")) {
            mecanico.setVisible(true);
            mecanico.setLocationRelativeTo(null);
            //VistaMecanico1 vistaMecanico = new VistaMecanico1();
            // vistaMecanico.setVisible(true);
            //vistaMecanico.setLocationRelativeTo(null);
        } else if (usuario.startsWith("con")) {
            conductor.setVisible(true);
            conductor.setLocationRelativeTo(null);
        } else if (usuario.startsWith("jtr")) {
            jefeTaller.setVisible(true);
            jefeTaller.setLocationRelativeTo(null);
        }
    }

    public static void main(String[] args) {
        ControlPrincipal controlPrincipal = new ControlPrincipal();

        controlPrincipal.login.setVisible(true);
        controlPrincipal.login.setLocationRelativeTo(null);

    }
}
