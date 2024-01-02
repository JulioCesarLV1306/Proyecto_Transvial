/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import DAOS.DAO;
import Modelo.Entidades.Bus;
import Modelo.Entidades.Conductor;
import Modelo.Conexion;
import Modelo.RegistroConductor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegistroConductorDAO implements DAO<RegistroConductor> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public RegistroConductor agregar(RegistroConductor RegC) {
        String sql = "INSERT INTO informeconductor1 (IdConductor, KmInicial, HoraInicio, IdBus, FechaInicial) VALUES ( ?, ?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, RegC.getConductor().getId());
            ps.setInt(2, RegC.getKmI());
            LocalTime horaActual = LocalTime.now();
            ps.setTime(3, java.sql.Time.valueOf(horaActual));
            ps.setInt(4, RegC.getBusI().getId());
            ps.setDate(5, new java.sql.Date(RegC.getFechaI().getTime()));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return RegC;
    }

    public RegistroConductor agregarInforme2(RegistroConductor RegC) {
        String sql = "INSERT INTO informeconductor2 (IdConductor, KmFinal, HoraFinal,IdBus, FechaFinal, FallaMecanica, Comentario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, RegC.getConductor().getId());
            ps.setInt(2, RegC.getKmF());
            LocalTime horaActual = LocalTime.now();
            ps.setTime(3, java.sql.Time.valueOf(horaActual));
            ps.setInt(4, RegC.getBusF().getId());
            ps.setDate(5, new java.sql.Date(RegC.getFechaF().getTime()));
            ps.setBoolean(6, RegC.isFallamecanica());
            ps.setString(7, RegC.getDescripRC());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return RegC;
    }

    @Override
    public int actualizar(RegistroConductor RegC) {
        String sql = "UPDATE informeConductor SET ConductorID=?, BusIDI=?, BusIDF=?, KMI=?, KMF=?, FechaI=?, FechaF=?, Descripcion=? WHERE ID = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, RegC.getConductor().getId());
            ps.setInt(2, RegC.getBusI().getId());
            ps.setInt(3, RegC.getBusF().getId());
            ps.setInt(4, RegC.getKmF());
            ps.setInt(5, RegC.getKmI());
            ps.setDate(6, (Date) RegC.getFechaI());
            ps.setDate(7, (Date) RegC.getFechaF());
            ps.setString(8, RegC.getDescripRC());
            ps.setInt(9, RegC.getIDRegC());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    @Override
    public void borrar(RegistroConductor RegC) {
        String sql = "DELETE FROM informeConductor WHERE ID = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, RegC.getIDRegC());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<RegistroConductor> listarTodos() {
        List<RegistroConductor> registrosRgC = new ArrayList<>();
        String sql = "SELECT ic1.ID, ic1.IdConductor, ic1.IdBus, ic1.KmInicial, ic2.KmFinal, ic1.HoraInicio, ic2.HoraFinal, ic1.FechaInicial, ic2.FechaFinal, ic2.FallaMecanica, ic2.Comentario\n"
                + "FROM informeconductor1 ic1\n"
                + "JOIN informeconductor2 ic2 ON ic1.ID = ic2.ID;";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RegistroConductor registroC = new RegistroConductor();
                Conductor conductor = new Conductor();
                Bus busI = new Bus();
                Bus busF = new Bus();
                registroC.setIDRegC(rs.getInt("ID"));
                conductor.setId(rs.getInt("IdConductor"));
                busI.setId(rs.getInt("IdBus")); // Corregido: cambia "BusIDI" por "IdBus"
                busF.setId(rs.getInt("IdBus")); // Corregido: cambia "BusIDF" por "IdBus"
                registroC.setKmI(rs.getInt("KmInicial"));
                registroC.setKmF(rs.getInt("KmFinal"));
                registroC.setHorainicial(rs.getTime("HoraInicio"));
                registroC.setHorafinal(rs.getTime("HoraFinal"));
                registroC.setFechaI(rs.getDate("FechaInicial"));
                registroC.setFechaF(rs.getDate("FechaFinal"));
                registroC.setDescripRC(rs.getString("Comentario")); // Corregido: cambia "Descripcion" por "Comentario"
                registroC.setFallamecanica(rs.getBoolean("FallaMecanica"));
                registroC.setConductor(conductor);
                registroC.setBusI(busI);
                registroC.setBusF(busF);

                registrosRgC.add(registroC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return registrosRgC;
    }

    @Override
    public RegistroConductor buscarPorID(int IDRegC) {
        String sql = "SELECT * FROM informeConductor WHERE ID = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDRegC);
            rs = ps.executeQuery();

            if (rs.next()) {
                RegistroConductor registroC = new RegistroConductor();
                registroC.setIDRegC(rs.getInt("ID"));
                registroC.setKmI(rs.getInt("KMI"));
                registroC.setKmF(rs.getInt("KMF"));
                registroC.setFechaI(rs.getDate("FechaI"));
                registroC.setFechaF(rs.getDate("FechaF"));
                registroC.setDescripRC(rs.getString("Descripcion"));

                Conductor conductor = new Conductor(rs.getInt("ConductorID"), "", "", "", "", "", false);
                Bus busI = new Bus();
                Bus busF = new Bus();
                busI.setId(rs.getInt("BusIDI"));
                busF.setId(rs.getInt("BusIDF"));

                registroC.setConductor(conductor);
                registroC.setBusI(busI);
                registroC.setBusF(busF);

                return registroC;
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
}
