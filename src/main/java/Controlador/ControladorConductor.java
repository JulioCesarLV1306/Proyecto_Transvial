/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAOS.BusDAO;
import DAOS.DAO;
import DAOS.PersonalDAO;
import DAOS.RegistroConductorDAO;
import Modelo.Entidades.Bus;
import Modelo.Entidades.Conductor;
import Modelo.Entidades.Personal;
import Modelo.RegistroConductor;
import Vistas.VistaConductor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorConductor implements ActionListener {

    private DAO<RegistroConductor> dao = new RegistroConductorDAO();
    private DAO<Bus> daob = new BusDAO();
    private DAO<Personal> daop = new PersonalDAO();
    private DefaultTableModel modelo = new DefaultTableModel();
    private VistaConductor vista;

    public ControladorConductor(VistaConductor vista) {
        this.vista = vista;
        this.vista.btnRegCon.addActionListener(this);
        this.vista.btnregistro2.addActionListener(this);
        this.vista.radbtnSi.addActionListener(this);
        cargarConductoresEnComboBox();
        cargarBusesEnComboBox();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegCon) {
            EnviarInforme1();
        }
        if (e.getSource() == vista.btnregistro2) {
            EnviarInforme2();
        }
        if(e.getSource()== vista.radbtnSi ){
        if(vista.radbtnSi.isSelected() ){
            vista.jTexComentarioCon.setEnabled(true);
            }else{ 
            vista.jTexComentarioCon.setEnabled(false);
        }
        
        }
    }

    public void EnviarInforme1() {
        RegistroConductor registro = new RegistroConductor();

        String idCoductorSeleccionado = (String) vista.jComboIDConductro.getSelectedItem();
        int idConductor = Integer.parseInt(idCoductorSeleccionado);
        String idBusSelecionado1 = (String) vista.jComboIDBUS1.getSelectedItem();
        int idBus1 = Integer.parseInt(idBusSelecionado1);

        int km1 = 0;
        String km1String = vista.jSpinnerKMinicial.getValue().toString();
        if (!km1String.isEmpty()) {
            km1 = Integer.parseInt(km1String);
        }

        LocalDate fecha1 = LocalDate.now();

        Conductor conductor = new Conductor();
        conductor.setId(idConductor);
        Bus bus = new Bus();
        bus.setId(idBus1);

        registro.setConductor(conductor);
        registro.setBusI(bus);
        registro.setKmI(km1);
        registro.setFechaI(Date.from(fecha1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        LocalTime horaActual = LocalTime.now();
        registro.setHorainicial(Time.valueOf(horaActual));

        RegistroConductor nuevoInforme = dao.agregar(registro);
        if (nuevoInforme != null) {
            JOptionPane.showMessageDialog(vista, "Informe agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void EnviarInforme2() {
        RegistroConductor registro2 = new RegistroConductor();

        String idCoductorSeleccionado = (String) vista.jComboConductor2.getSelectedItem();
        int idConductor = Integer.parseInt(idCoductorSeleccionado);
        String idBusSelecionado2 = (String) vista.jcomboIdbus2.getSelectedItem();
        int idBus2 = Integer.parseInt(idBusSelecionado2);
        int km2 = 0;
        String km2String = vista.jSpinnerKMFinal.getValue().toString();
        if (!km2String.isEmpty()) { // Corregido: cambia la condición a !km2String.isEmpty()
            km2 = Integer.parseInt(km2String);
        }
        LocalDate fecha2 = LocalDate.now();
        boolean fallaMecanica = vista.radbtnSi.isSelected();
        String descripcion = vista.jTexComentarioCon.getText();
        Conductor conductor = new Conductor();
        conductor.setId(idConductor);
        Bus bus = new Bus();
        bus.setId(idBus2);

        registro2.setConductor(conductor);
        registro2.setBusF(bus);
        registro2.setKmF(km2);
        registro2.setFechaF(Date.from(fecha2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        registro2.setDescripRC(descripcion);
        registro2.setFallamecanica(fallaMecanica);
        LocalTime horaActual = LocalTime.now();
        registro2.setHorafinal(Time.valueOf(horaActual));
        RegistroConductor nuevoInforme2 = dao. agregarInforme2(registro2);
        if (nuevoInforme2 != null) {
            JOptionPane.showMessageDialog(vista, "Informe agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void cargarConductoresEnComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Paso 1: Obtener los datos de la tabla "personal"
        List<Personal> personalList = daop.listarTodos();// Asumiendo que tienes un método para obtener los datos de la tabla "personal"

        // Paso 2: Agregar los datos al modelo del combo box
        for (Personal personal : personalList) {
            if (personal.getCargo().equals("Conductor") && personal.isDisponibilidad()) {
                comboBoxModel.addElement(String.valueOf(personal.getId()));
            }
        }

        // Paso 3: Configurar el modelo del combo box
        vista.jComboIDConductro.setModel(comboBoxModel);
        vista.jComboConductor2.setModel(comboBoxModel);

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
        vista.jComboIDBUS1.setModel(comboBoxModel);
        vista.jcomboIdbus2.setModel(comboBoxModel);

    }
    public static void main(String[] args) {
        VistaConductor v = new VistaConductor();
        ControladorConductor c = new ControladorConductor(v);
        
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
}
