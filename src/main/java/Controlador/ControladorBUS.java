package Controlador;

import Modelo.Entidades.Bus;
import DAOS.BusDAO;
import DAOS.DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.JefeTaller;
import java.nio.file.Files;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Julio López
 */
public class ControladorBUS implements ActionListener {

    private DAO<Bus> dao = new BusDAO();
    private Bus bus = new Bus();
    private JefeTaller vista;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ControladorBUS(JefeTaller v) {
        this.vista = v;
        this.vista.btnListarbus.addActionListener(this);
        this.vista.btnAgregarBUS.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminarBus.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListarbus) {
            limpiarTabla();
            listarBus(vista.Tablabus);
        } else if (e.getSource() == vista.btnAgregarBUS) {
            agregarBUS();
            limpiarTabla();
            listarBus(vista.Tablabus);
        } else if (e.getSource() == vista.btnEditar) {
            int fila = vista.Tablabus.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                vista.MenudePaneles.setSelectedIndex(3);
                int id = Integer.parseInt(vista.Tablabus.getValueAt(fila, 0).toString());
                String placa = (String) vista.Tablabus.getValueAt(fila, 1);
                String modelo = (String) vista.Tablabus.getValueAt(fila, 2);
                int km = Integer.parseInt(vista.Tablabus.getValueAt(fila, 3).toString());
                String estado=(String)vista.Tablabus.getValueAt(fila, 4);

                vista.txtIDBus.setText(String.valueOf(id));
                vista.txtPlaca.setText(placa);
                vista.txtModelo.setText(modelo);
                vista.jSpinnerKM.setValue(km);
                vista.jComboEstadoBUs.setSelectedItem(estado);
            }
        } else if (e.getSource() == vista.btnActualizar) {
            ActualizarBUS();
            limpiarTabla();
            listarBus(vista.Tablabus);
        } else if (e.getSource() == vista.btnEliminarBus) {
            eliminar();
            limpiarTabla();
            listarBus(vista.Tablabus);
        }
    }

    public void eliminar() {
        int fila = vista.Tablabus.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un Bus");
        } else {
            int id = Integer.parseInt(vista.Tablabus.getValueAt(fila, 0).toString());
            Modelo.Entidades.Bus bu = dao.buscarPorID(id);
            if (bu != null) {
                dao.borrar(bu);
                JOptionPane.showMessageDialog(vista, "Bus eliminado");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
    }

    public void ActualizarBUS() {
        int id = Integer.parseInt(vista.txtIDBus.getText());
        String placa = vista.txtPlaca.getText();
        String modelo = vista.txtModelo.getText();
        int km = 0; // Valor predeterminado en caso de cadena vacía o nula
        String kmString = vista.jSpinnerKM.getValue().toString();
        String estado=vista.jComboEstadoBUs.getSelectedItem().toString();
        if (!kmString.isEmpty()) {
            km = Integer.parseInt(kmString);
        }
        bus.setId(id);
        bus.setPlaca(placa);
        bus.setModelo(modelo);
        bus.setKm(km);
        bus.setEstado(estado);
        int r = dao.actualizar(bus);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Bus actualizado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void agregarBUS() {
        String placa = vista.txtPlaca.getText();
        String modelo = vista.txtModelo.getText();
        int km = Integer.parseInt(vista.jSpinnerKM.getValue().toString());
        String estado = (String) vista.jComboEstadoBUs.getSelectedItem().toString();
        bus.setPlaca(placa);
        bus.setModelo(modelo);
        bus.setKm(km);
        bus.setEstado(estado);
        Bus nuevoBus = dao.agregar(bus);
        if (nuevoBus != null) {
            JOptionPane.showMessageDialog(vista, "Bus agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }

    }

    public void listarBus(JTable TablaBus) {
        modelo = (DefaultTableModel) TablaBus.getModel();
        List<Bus> lista = dao.listarTodos();
        Object[] object = new Object[5];
        for (Bus bus : lista) {
            object[0] = bus.getId();
            object[1] = bus.getPlaca();
            object[2] = bus.getModelo();
            object[3] = bus.getKm();
            object[4] = bus.getEstado();
            modelo.addRow(object);
        }
        vista.Tablabus.setModel(modelo);
    }

    void limpiarTabla() {
        modelo.setRowCount(0);
        vista.txtPlaca.setText("");
        vista.txtModelo.setText("");
        vista.jSpinnerKM.setValue(0);
    }
    
    //EJECUTAR PROGRAMA-----
    /*public static void main(String[] args) {
        JefeTaller v = new JefeTaller();
        ControladorBUS c = new ControladorBUS(v);
        ControladorPersonal cp=new ControladorPersonal(v);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }*/
}
