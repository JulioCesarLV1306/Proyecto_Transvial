package DAOS;

import Modelo.Entidades.Bus;
import Modelo.Conexion;
import Modelo.RegistroConductor;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class BusDAO implements DAO<Bus> {

    private Conexion conectar = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Bus agregar(Bus b) {
        String sql = "INSERT INTO flotabuses (PLACA, MODELO, KILOMETRAJE,ESTADO) VALUES (?, ?, ?, ?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, b.getPlaca());
            ps.setString(2, b.getModelo());
            ps.setInt(3, b.getKm());
            ps.setString(4, b.getEstado());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                b.setId(idGenerado);
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null;
    }

    @Override
    public int actualizar(Bus b) {
        String sql = "UPDATE flotabuses SET PLACA = ?, MODELO = ?, KILOMETRAJE = ?, ESTADO = ? WHERE IdBus = ?";
        int filasActualizadas = 0;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, b.getPlaca());
            ps.setString(2, b.getModelo());
            ps.setInt(3, b.getKm());
            ps.setString(4, b.getEstado());
            ps.setInt(5, b.getId());
           filasActualizadas = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return filasActualizadas;

    }

    @Override
    public void borrar(Bus bus) {
        String sql = "DELETE FROM flotabuses WHERE IdBus = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, bus.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Bus> listarTodos() {
        List<Bus> datos = new ArrayList<>();
        String sql = "SELECT * FROM flotabuses";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bus b = new Bus();
                b.setId(rs.getInt("IdBus"));
                b.setPlaca(rs.getString("PLACA"));
                b.setModelo(rs.getString("MODELO"));
                b.setKm(rs.getInt("KILOMETRAJE"));
                b.setEstado(rs.getString("ESTADO"));
                datos.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return datos;
    }

    @Override
    public Bus buscarPorID(int id) {
    Bus bus = null;
        String sql = "SELECT * FROM flotabuses WHERE IdBus = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                bus = new Bus();
                bus.setId(rs.getInt("IdBus"));
                bus.setPlaca(rs.getString("PLACA"));
                bus.setModelo(rs.getString("MODELO"));
                bus.setKm(rs.getInt("KILOMETRAJE"));
                bus.setEstado(rs.getString("ESTADO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return bus;
       

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
