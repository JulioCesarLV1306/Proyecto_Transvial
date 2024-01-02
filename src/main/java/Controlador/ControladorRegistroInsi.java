package Controlador;

import DAOS.RegistroMecanicoDAO;
import DAOS.BusDAO;
import DAOS.DAO;
import DAOS.PersonalDAO;
import DAOS.RegistroConductorDAO;
import DAOS.RegistroIncidenciasDAO;
import Modelo.Entidades.Bus;
import Modelo.Entidades.Mecanico;
import Modelo.Entidades.Personal;
import Modelo.PersonalComboBoxRenderer;
import Modelo.RegistroConductor;
import Modelo.RegistroIncidencias;
import Modelo.RegistroMecanico;
import Vistas.JefeTaller;
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

public class ControladorRegistroInsi implements ActionListener {

    private DAO<Bus> daob = new BusDAO();
    private DAO<Personal> daop = new PersonalDAO();
    private DAO<RegistroMecanico> daomec = new RegistroMecanicoDAO();
    private DAO<RegistroConductor> daocon = new RegistroConductorDAO();
    private DAO<RegistroIncidencias> dao = new RegistroIncidenciasDAO();
    private JefeTaller vista;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ControladorRegistroInsi(JefeTaller vista) {
        this.vista = vista;
        this.vista.btnEnviarInforme.addActionListener(this);
        this.vista.btnListarHistorial.addActionListener(this);
        this.vista.btnListarInformeMecanico.addActionListener(this);
        this.vista.btnListarInformeConductor.addActionListener(this);
        cargarMecanicosEnComboBox();
        cargarBusesEnComboBox();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnEnviarInforme) {
            EnviarInforme();

        }
        if (e.getSource() == vista.btnListarHistorial) {
            limpiarTabla();
            listarInforme(vista.jTableHistorial);

        }
        if (e.getSource() == vista.btnListarInformeMecanico) {
            limpiarTabla();
            listarinformeMecanico(vista.TablaInformeMecanico);
        }
        if (e.getSource() == vista.btnListarInformeConductor) {
            limpiarTabla();
            listarinformeConductor(vista.TablaInformeConductor);
        }

    }

    public void EnviarInforme() {
        RegistroIncidencias registro = new RegistroIncidencias();

        String idMecanicoSeleccionado = (String) vista.jComboMecanico.getSelectedItem();
        int idMecanico = Integer.parseInt(idMecanicoSeleccionado);
        String periodo = vista.jComboPeriodo.getSelectedItem().toString();
        String idBusSelecionado = (String) vista.jComboIDbus.getSelectedItem();
        int idBus = Integer.parseInt(idBusSelecionado);
        LocalDate fecha = LocalDate.now();
        String descrip = vista.texareaDescrip.getText();
        Bus bus = new Bus();
        bus.setId(idBus);
        Mecanico mecanico = new Mecanico();
        mecanico.setId(idMecanico);
        String selectedItem = (String) vista.jComboSanjas.getSelectedItem();
        int numSanja = Integer.parseInt(selectedItem);

        registro.setMecanico(mecanico);
        registro.setPeriodoInforme(periodo);
        registro.setBus(bus);
        registro.setFecha(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        registro.setDescripcion(descrip);
        registro.setNumeroSanja(numSanja);

        RegistroIncidencias nuevoInforme = dao.agregar(registro);
        if (nuevoInforme != null) {
            JOptionPane.showMessageDialog(vista, "Informe agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void cargarMecanicosEnComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Paso 1: Obtener los datos de la tabla "personal"
        List<Personal> personalList = daop.listarTodos();// Asumiendo que tienes un método para obtener los datos de la tabla "personal"

        // Paso 2: Agregar los datos al modelo del combo box
        for (Personal personal : personalList) {
            if (personal.getCargo().equals("Mecanico") && personal.isDisponibilidad()) {
                comboBoxModel.addElement(String.valueOf(personal.getId()));
            }
        }

        // Paso 3: Configurar el modelo del combo box
        vista.jComboMecanico.setModel(comboBoxModel);

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
        vista.jComboIDbus.setModel(comboBoxModel);

    }

    public void listarInforme(JTable TablaHistorial) {
        modelo = (DefaultTableModel) TablaHistorial.getModel();
        List<RegistroIncidencias> lista = dao.listarTodos();
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
        vista.jTableHistorial.setModel(modelo);
    }

    public void listarinformeMecanico(JTable TablaInformeMecanico) {
        modelo = (DefaultTableModel) TablaInformeMecanico.getModel();
        List<RegistroMecanico> lista = daomec.listarTodos();
        Object[] object = new Object[10];
        for (RegistroMecanico registro : lista) {
            object[0] = registro.getId();
            object[1] = registro.getBus().getId();
            object[2] = registro.getPeriodoHoras();
            object[3] = registro.getEstado();
            object[4] = registro.getDireccion();
            object[5] = registro.getSuspension();
            object[6] = registro.getTransmision();
            object[7] = registro.getCorona();
            object[8] = registro.getMotor();
            object[9] = registro.getComentario();
            modelo.addRow(object);
        }
        vista.TablaInformeMecanico.setModel(modelo);

    }

    public void listarinformeConductor(JTable TablainformeConductor) {
        modelo = (DefaultTableModel) TablainformeConductor.getModel();
        List<RegistroConductor> lista = daocon.listarTodos();
        Object[] object = new Object[11];
        for (RegistroConductor registro : lista) {
            object[0] = registro.getIDRegC();
            object[1] = registro.getConductor().getId();
            object[2] = registro.getBusI().getId();
            object[3] = registro.getKmI();
            object[4] = registro.getKmF();
            object[5] = registro.getHorainicial();
            object[6] = registro.getHorafinal();
            object[7] = registro.getFechaI();
            object[8] = registro.getFechaF();
            object[9] = registro.isFallamecanica();
            object[10] = registro.getDescripRC();
            modelo.addRow(object);
        }
        vista.TablaInformeConductor.setModel(modelo);
    }

    void limpiarTabla() {
        modelo.setRowCount(0);
    }

}
