/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import DAOS.DAO;
import Modelo.Entidades.Bus;
import Modelo.Conexion;
import Modelo.Entidades.Mecanico;
import Modelo.Entidades.Personal;
import Modelo.RegistroConductor;
import Modelo.RegistroIncidencias;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Julio López
 */
public class RegistroIncidenciasDAO implements DAO<RegistroIncidencias> {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public RegistroIncidencias agregar(RegistroIncidencias registro) {
        String sql = "INSERT INTO informes (MecanicoID, PeriodoInforme, BusID, Fecha, Descripcion,NumeroSanja) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, registro.getMecanico().getId());
            ps.setString(2, registro.getPeriodoInforme());
            ps.setInt(3, registro.getBus().getId());
            ps.setDate(4, new java.sql.Date(registro.getFecha().getTime()));
            ps.setString(5, registro.getDescripcion());
            ps.setInt(6, registro.getNumeroSanja());
            ps.executeUpdate();
            return registro;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null;

    }

    

    @Override
    public int actualizar(RegistroIncidencias registro) {
        String sql = "UPDATE informes SET MecanicoID = ?, PeriodoInforme = ?, BusID = ?, Fecha = ?, Descripcion = ? WHERE ID = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, registro.getMecanico().getId());
            ps.setString(2, registro.getPeriodoInforme());
            ps.setInt(3, registro.getBus().getId());
            ps.setDate(4, (Date) registro.getFecha());
            ps.setString(5, registro.getDescripcion());
            ps.setInt(6, registro.getID());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    @Override
    public void borrar(RegistroIncidencias registro) {
        String sql = "DELETE FROM informes WHERE ID = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, registro.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<RegistroIncidencias> listarTodos() {
        List<RegistroIncidencias> registros = new ArrayList<>();
        String sql = "SELECT * FROM informes";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RegistroIncidencias registro = new RegistroIncidencias();
                Mecanico mecanico=new Mecanico();
                Bus bus = new Bus();
                registro.setID(rs.getInt("ID"));
                mecanico.setId(rs.getInt("MecanicoID"));
                registro.setPeriodoInforme(rs.getString("PeriodoInforme"));
                bus.setId(rs.getInt("BusID"));
                registro.setFecha(rs.getDate("Fecha"));
                registro.setDescripcion(rs.getString("Descripcion"));
                registro.setNumeroSanja(rs.getInt("NumeroSanja"));
                registro.setMecanico(mecanico);
                registro.setBus(bus);

                registros.add(registro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return registros;
    }

    @Override
    public RegistroIncidencias buscarPorID(int ID) {
        String sql = "SELECT * FROM informes WHERE ID = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();

            if (rs.next()) {
                RegistroIncidencias registro = new RegistroIncidencias();
                registro.setID(rs.getInt("ID"));
                registro.setPeriodoInforme(rs.getString("PeriodoInforme"));
                registro.setFecha(rs.getDate("Fecha"));
                registro.setDescripcion(rs.getString("Descripcion"));

                Mecanico mecanico = new Mecanico(rs.getInt("MecanicoID"), "", "", "", "", "", false);
                Bus bus = new Bus();
                bus.setId(rs.getInt("BusID"));

                registro.setMecanico(mecanico);
                registro.setBus(bus);

                return registro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RegistroConductor agregarInforme2(RegistroConductor registro2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
