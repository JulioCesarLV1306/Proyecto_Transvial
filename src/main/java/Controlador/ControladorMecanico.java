/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAOS.BusDAO;
import DAOS.DAO;
import DAOS.RegistroIncidenciasDAO;
import DAOS.RegistroMecanicoDAO;
import Modelo.Entidades.Bus;
import Modelo.Entidades.Mecanico;
import Modelo.RegistroIncidencias;
import Modelo.RegistroMecanico;
import Vistas.VistaMecanico1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio López
 */
public class ControladorMecanico implements ActionListener {
    
    private DAO<RegistroIncidencias> daor = new RegistroIncidenciasDAO();
    private DAO<RegistroMecanico> dao = new RegistroMecanicoDAO();
    private DAO<Bus> daob = new BusDAO();
    private DefaultTableModel modelo = new DefaultTableModel();
    private VistaMecanico1 vista;
    
    
    public ControladorMecanico(VistaMecanico1 vista) {
        this.vista = vista;
        this.vista.btnListarinforme.addActionListener(this);
        this.vista.btnDireccion.addActionListener(this);
        this.vista.bntSuspencion.addActionListener(this);
        this.vista.btnTransmision.addActionListener(this);
        this.vista.btnCorona.addActionListener(this);
        this.vista.btnMotor.addActionListener(this);
        this.vista.btnEnviarInforme.addActionListener(this);
        this.vista.BtnModoOscuro.addActionListener(this);
        cargarBusesEnComboBox();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListarinforme) {
            limpiarTabla();
            listarInforme(vista.tablaInforme);
            
        }
        if (e.getSource() == vista.btnDireccion) {
            if (vista.btnDireccion.isSelected()) {
                vista.jcomboDireccion.setEnabled(true);
                vista.panelDireccion.setBackground(Color.red);
            } else {
                vista.jcomboDireccion.setEnabled(false);
                vista.panelDireccion.setBackground(null);
            }
        }
        
        if (e.getSource() == vista.bntSuspencion) {
            if (vista.bntSuspencion.isSelected()) {
                vista.jComboSuspencion.setEnabled(true);
                vista.panelSuspencion.setBackground(Color.red);
            } else {
                vista.jComboSuspencion.setEnabled(false);
                vista.panelSuspencion.setBackground(null); // Opcionalmente, puedes restaurar el color por defecto
            }
        }
        if (e.getSource() == vista.btnTransmision) {
            if (vista.btnTransmision.isSelected()) {
                vista.jComboTransmision.setEnabled(true);
                vista.panelTrnas.setBackground(Color.red);
            } else {
                vista.jComboTransmision.setEnabled(false);
                vista.panelTrnas.setBackground(null);
            }
            
        }
        if (e.getSource() == vista.btnCorona) {
            if (vista.btnCorona.isSelected()) {
                vista.jcomboCorona.setEnabled(true);
                vista.panelCOrona.setBackground(Color.RED);
            } else {
                vista.jcomboCorona.setEnabled(false);
                vista.panelCOrona.setBackground(null);
            }
        }
        if (e.getSource() == vista.btnMotor) {
            if (vista.btnMotor.isSelected()) {
                vista.jComboMotor.setEnabled(true);
                vista.panelMotor.setBackground(Color.red);
                
            } else {
                vista.jComboMotor.setEnabled(false);
                vista.panelMotor.setBackground(null);
                
            }
            
        }
        if(e.getSource()== vista.btnEnviarInforme){
        EnviarInforme();
        }
        //Codigo para el modo Noche En MECANICO
        if(e.getSource()==vista.BtnModoOscuro){
        if(vista.BtnModoOscuro.isSelected()){
            vista.panelLogo.setBackground(Color.black);
            vista.jPanelBtns.setBackground(Color.BLACK);
            vista.panelUsuario.setBackground(Color.BLACK);
            vista.panelBtnInforme.setBackground(new Color(102, 102, 102));
            vista.JpaneRepor.setBackground(new Color(102, 102, 102));
            vista.btnCerrar.setBackground(new Color(102, 102, 102));
            
            
            
        }else{
            vista.panelLogo.setBackground(new Color(143,207,254));
            vista.jPanelBtns.setBackground(new Color(143,207,254));
            vista.panelUsuario.setBackground(new Color(143,207,254));
             vista.panelBtnInforme.setBackground(new Color(14,172,209));
            vista.JpaneRepor.setBackground(new Color(14,172,209));
            vista.btnCerrar.setBackground(new Color(143,207,254));
            
        }
        
        }
       
    }
    
    public void listarInforme(JTable Tablainforme) {
        modelo = (DefaultTableModel) Tablainforme.getModel();
        List<RegistroIncidencias> lista = daor.listarTodos();
        Object[] object = new Object[7];
        for (RegistroIncidencias registro : lista) {
            object[0] = registro.getID();
            object[1] = registro.getMecanico().getId();
            object[2] = registro.getPeriodoInforme();
            object[3] = registro.getBus().getId();
            object[4] = registro.getFecha();
            object[5] = registro.getDescripcion();
            object[6] = registro.getNumeroSanja();
            modelo.addRow(object);
        }
        vista.tablaInforme.setModel(modelo);
    }

    public void EnviarInforme() {
        RegistroMecanico registro = new RegistroMecanico();
        String idBusSelecionado = (String) vista.jComboIdBus.getSelectedItem();
        int idBus = Integer.parseInt(idBusSelecionado);
        int periodohoras = 0;
        String periodohorasString = vista.jSpinnerHoras.getValue().toString();
        if (!periodohorasString.isEmpty()) {
            periodohoras = Integer.parseInt(periodohorasString);
        }
        String estado = vista.jComboEstado.getSelectedItem().toString();
        String direccion = vista.jcomboDireccion.getSelectedItem().toString();
        String suspencion = vista.jComboSuspencion.getSelectedItem().toString();
        String transmision = vista.jComboTransmision.getSelectedItem().toString();
        String corona = vista.jcomboCorona.getSelectedItem().toString();
        String motor = vista.jComboMotor.getSelectedItem().toString();
        String comentario = vista.jTextComentario.getText();
        
        Bus bus = new Bus();
        bus.setId(idBus);
        
        registro.setBus(bus);
        registro.setPeriodoHoras(periodohoras);
        registro.setEstado(estado);
        registro.setDireccion(direccion);
        registro.setSuspension(suspencion);
        registro.setTransmision(transmision);
        registro.setCorona(corona);
        registro.setMotor(motor);
        registro.setComentario(comentario);
        RegistroMecanico nuevoInforme = dao.agregar(registro);
        if (nuevoInforme != null) {
            JOptionPane.showMessageDialog(vista, "Informe agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        
    }

    public void cargarBusesEnComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Paso 1: Obtener los datos de la tabla "personal"
        List<Bus> busList = daob.listarTodos();

        // Paso 2: Agregar los datos al modelo del combo box
        for (Bus bus : busList) {
            if (bus.getEstado().equals("Operativo")) {
                comboBoxModel.addElement(String.valueOf(bus.getId()));
            }
        }

        // Paso 3: Configurar el modelo del combo box
        vista.jComboIdBus.setModel(comboBoxModel);
        
    }
    
    void limpiarTabla() {
        modelo.setRowCount(0);
    }
    
    /*public static void main(String[] args) {
        
        VistaMecanico1 v = new VistaMecanico1();
        ControladorMecanico c = new ControladorMecanico(v);
        
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }*/
}
