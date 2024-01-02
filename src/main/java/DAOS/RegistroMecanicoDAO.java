/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Modelo.Entidades.Bus;
import Modelo.Entidades.Mecanico;
import Modelo.RegistroMecanico;
import java.util.List;
import Modelo.Conexion;
import Modelo.RegistroConductor;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class RegistroMecanicoDAO implements DAO<RegistroMecanico> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public RegistroMecanico agregar(RegistroMecanico registroMec) {
        String sql = "INSERT INTO informemecánico (IdBus, PeriodoTiempo_hrs,Estado,Direccion,Suspencion, Transmision, Corona, Motor,Comentario ) values ( ?, ?, ?, ?, ?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, registroMec.getBus().getId());
            ps.setInt(2, registroMec.getPeriodoHoras());
            ps.setString(3, registroMec.getEstado());
            ps.setString(4, registroMec.getDireccion());
            ps.setString(5, registroMec.getSuspension());
            ps.setString(6, registroMec.getTransmision());
            ps.setString(7, registroMec.getCorona());
            ps.setString(8, registroMec.getMotor());
            ps.setString(9, registroMec.getComentario());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return registroMec;
    }

    @Override
    public int actualizar(RegistroMecanico entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(RegistroMecanico entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<RegistroMecanico> listarTodos() {
         List<RegistroMecanico> registros = new ArrayList<>();
        String sql = "SELECT * FROM informemecánico";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RegistroMecanico registro = new RegistroMecanico();
                Bus bus = new Bus();
                registro.setId(rs.getInt("ID"));
                bus.setId(rs.getInt("IdBus"));
                registro.setPeriodoHoras(rs.getInt("PeriodoTiempo_hrs"));
                registro.setEstado(rs.getString("Estado"));
                registro.setDireccion(rs.getString("Direccion"));
                registro.setSuspension(rs.getString("Suspencion"));
                registro.setTransmision(rs.getString("Transmision"));
                registro.setCorona(rs.getString("Corona"));
                registro.setMotor(rs.getString("Motor"));
                registro.setComentario(rs.getString("Comentario"));
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
    public RegistroMecanico buscarPorID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
