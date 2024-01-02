package Controlador;

import DAOS.DAO;
import Modelo.Entidades.Personal;
import DAOS.PersonalDAO;
import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.JefeTaller;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ControladorPersonal implements ActionListener {

    private JefeTaller vista;
    private DAO<Personal> dao = new PersonalDAO();
    private Personal personal = new Personal();
    private DefaultTableModel modelo = new DefaultTableModel();

    public ControladorPersonal(JefeTaller vista) {
        this.vista = vista;
        this.vista.btnListarPersonal.addActionListener(this);
        this.vista.btnAgregarPersonal.addActionListener(this);
        this.vista.btnEditarPersonal.addActionListener(this);
        this.vista.btnActualizarPersonal.addActionListener(this);
        this.vista.btnEliminarPersonal.addActionListener(this);
        this.vista.BtnagregarSelecindex.addActionListener(this);
        this.vista.btnAgregarBusSelcindex.addActionListener(this);
        this.vista.BtnImprimir.addActionListener(this);
        this.vista.BtnImprimirBus.addActionListener(this);
        this.vista.ImprimrirIformeMec.addActionListener(this);
        this.vista.ImprimirInformeConduc.addActionListener(this);
        this.vista.ImprimirInformesjdt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListarPersonal) {
            limpiartodo();
            listarPersonal(vista.TablaPersonal);
        }

        if (e.getSource() == vista.btnAgregarPersonal) {
            agregarPersonal();
            limpiartodo();
            listarPersonal(vista.TablaPersonal);
        }
        if (e.getSource() == vista.btnEditarPersonal) {
            
            int fila = vista.TablaPersonal.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                vista.MenudePaneles.setSelectedIndex(1);
                int id = Integer.parseInt(vista.TablaPersonal.getValueAt(fila, 0).toString());
                String nombres = (String) vista.TablaPersonal.getValueAt(fila, 1);
                String apellidos = (String) vista.TablaPersonal.getValueAt(fila, 2);
                String cargo = (String) vista.TablaPersonal.getValueAt(fila, 3);
                String nomUsuario = (String) vista.TablaPersonal.getValueAt(fila, 4);
                String contrasenia = (String) vista.TablaPersonal.getValueAt(fila, 5);
                boolean disponibilidad = (boolean) vista.TablaPersonal.getValueAt(fila, 6);

                vista.txtId.setText(String.valueOf(id));
                vista.txtNombre.setText(nombres);
                vista.txtApellido.setText(apellidos);
                vista.JCargo.setSelectedItem(cargo);
                vista.txtNomUser.setText(nomUsuario);
                vista.txtContrasenia.setText(contrasenia);
                vista.RbtnSi.setSelected(disponibilidad);
                vista.RbtnNo.setSelected(!disponibilidad);
            }
        }
        if (e.getSource() == vista.btnActualizarPersonal) {
            actualizarPersonal();
            limpiartodo();
            listarPersonal(vista.TablaPersonal);
        }
        if (e.getSource() == vista.btnEliminarPersonal) {
            eliminarPersonal();
            limpiartodo();
            listarPersonal(vista.TablaPersonal);
        }
        //Botones para navegar por el panel
        if(e.getSource()==vista.BtnagregarSelecindex){
        vista.MenudePaneles.setSelectedIndex(1);
        }
        if(e.getSource()==vista.btnAgregarBusSelcindex){
        vista.MenudePaneles.setSelectedIndex(3);
        }
        if(e.getSource()==vista.BtnImprimir){
            try {
                // vista.BtnImprimir;
                Conexion con = new Conexion();
                Connection connec= con.getConnection();
                JasperReport reporte=null;
                String path = "D:\\desarrollo\\ProyectTransvial\\src\\main\\resources\\Reportes\\ReporteDeEmpleados.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(path,null,connec);
                JasperViewer view= new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==vista.BtnImprimirBus){
        try {
                
                Conexion con = new Conexion();
                Connection connec= con.getConnection();
                JasperReport reporte=null;
                String path = "D:\\desarrollo\\ProyectTransvial\\src\\main\\resources\\Reportes\\ReporteDeBuses.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(path,null,connec);
                JasperViewer view= new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        if(e.getSource()==vista.ImprimrirIformeMec){
        try {
                
                Conexion con = new Conexion();
                Connection connec= con.getConnection();
                JasperReport reporte=null;
                String path = "D:\\desarrollo\\ProyectTransvial\\src\\main\\resources\\Reportes\\ReporteMecanico.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(path,null,connec);
                JasperViewer view= new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        if(e.getSource()==vista.ImprimirInformeConduc){
        try {
                
                Conexion con = new Conexion();
                Connection connec= con.getConnection();
                JasperReport reporte=null;
                String path = "D:\\desarrollo\\ProyectTransvial\\src\\main\\resources\\Reportes\\ReporteConductor.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(path,null,connec);
                JasperViewer view= new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        if(e.getSource()==vista.ImprimirInformesjdt){
            try {
                
                Conexion con = new Conexion();
                Connection connec= con.getConnection();
                JasperReport reporte=null;
                String path = "D:\\desarrollo\\ProyectTransvial\\src\\main\\resources\\Reportes\\ReporteJefeTaller.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(path,null,connec);
                JasperViewer view= new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
    }

    public void listarPersonal(JTable TablaPersonal) {
        modelo = (DefaultTableModel) TablaPersonal.getModel();

        List<Personal> lista = dao.listarTodos();

        for (Personal personal : lista) {
            Object[] object = new Object[7];
            object[0] = personal.getId();
            object[1] = personal.getNombres();
            object[2] = personal.getApellidos();
            object[3] = personal.getCargo();
            object[4] = personal.getNomUsuario();
            object[5] = personal.getContrasenia();
            object[6] = personal.isDisponibilidad();
            modelo.addRow(object);
        }

        vista.TablaPersonal.setModel(modelo);
    }

    public void agregarPersonal() {
        String nombres = vista.txtNombre.getText();
        String apellidos = vista.txtApellido.getText();
        String cargo = (String) vista.JCargo.getSelectedItem().toString();
        String nomUsuario = vista.txtNomUser.getText();
        String contrasenia = vista.txtContrasenia.getText();
        boolean disponibilidad = vista.RbtnSi.isSelected();
        personal.setNombres(nombres);
        personal.setApellidos(apellidos);
        personal.setCargo(cargo);
        personal.setNomUsuario(nomUsuario);
        personal.setContrasenia(contrasenia);
        personal.setDisponibilidad(disponibilidad);
        Personal nuevoPersonal = dao.agregar(personal);
        if (nuevoPersonal != null) {
            JOptionPane.showMessageDialog(vista, "Personal agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void actualizarPersonal() {
        
        int id = Integer.parseInt(vista.txtId.getText());
        String nombres = vista.txtNombre.getText();
        String apellidos = vista.txtApellido.getText();
        String cargo = (String) vista.JCargo.getSelectedItem();
        String nomUsuario = vista.txtNomUser.getText();
        String contrasenia = vista.txtContrasenia.getText();
        boolean disponibilidad = vista.RbtnSi.isSelected();
        Personal per = new Personal(id, nombres, apellidos, cargo, nomUsuario, contrasenia, disponibilidad);
        per.setId(id);
        per.setNombres(nombres);
        per.setApellidos(apellidos);
        per.setCargo(cargo);
        per.setNomUsuario(nomUsuario);
        per.setContrasenia(contrasenia);
        per.setDisponibilidad(disponibilidad);
        int resultado = dao.actualizar(per);
        if(resultado==1){
            JOptionPane.showMessageDialog(vista, "Personal actualizado con éxito");
        }else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
      
    }

    public void eliminarPersonal() {
        int fila = vista.TablaPersonal.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt(vista.TablaPersonal.getValueAt(fila, 0).toString());
            Personal per = dao.buscarPorID(id);
            if (per != null) {
                dao.borrar(per);
                JOptionPane.showMessageDialog(vista, "Personal eliminado");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
    }

    void limpiartodo() {
        modelo.setRowCount(0);
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtNomUser.setText("");
        vista.txtContrasenia.setText("");
        vista.RbtnSi.setSelected(false);
        vista.RbtnNo.setSelected(false);
    }
}
