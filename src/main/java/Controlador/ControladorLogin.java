/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Entidades.Personal;
import DAOS.PersonalDAO;
import Vistas.Login;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorLogin implements ActionListener {

    private PersonalDAO dao = new PersonalDAO();
    private Login vista;

    public ControladorLogin(Login v) {
        this.vista = v;
        this.vista.bntIniciarSession.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.txtNombreUsuario.addActionListener(this);
        
        //CODIGO PARA EL TEXTO POR DEFECTO
        String textoPorDefecto = "Ingrese Usuario";
        vista.txtNombreUsuario.setText(textoPorDefecto);
        vista.txtNombreUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.txtNombreUsuario.getText().equals(textoPorDefecto)) {
                    vista.txtNombreUsuario.setText("");
                }
            }
        });
        vista.txtNombreUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (vista.txtNombreUsuario.getText().isEmpty()) {
                    vista.txtNombreUsuario.setText(textoPorDefecto);
                }
            }
        });
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.bntIniciarSession) {
            iniciarSesion();
        }
        if (e.getSource() == vista.btnCancelar) {
            System.exit(0);
        }
        

    }

    private void iniciarSesion() {

        String usuario = vista.txtNombreUsuario.getText();
        String contrasenia = new String(vista.txtContrasena.getPassword());
        Personal personal = dao.obtenerPersonalPorUsuarioContraseña(usuario, contrasenia);

        if (personal != null) {
         
            JOptionPane.showMessageDialog(vista, "Inicio de sesión exitoso. BIENVENIDO " + usuario + "!");

            ControlPrincipal controladorPrincipal = new ControlPrincipal();
            controladorPrincipal.abrirVentanaUsuario(usuario);

            vista.dispose();
        }
    }

    public static void main(String[] args) {
        Vistas.Login v = new Login();
        ControladorLogin c = new ControladorLogin(v);
        v.setVisible(true);
    }
}
