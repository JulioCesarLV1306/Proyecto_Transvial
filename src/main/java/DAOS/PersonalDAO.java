package DAOS;

import Modelo.Conexion;
import Modelo.Entidades.Personal;
import Modelo.RegistroConductor;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class PersonalDAO implements DAO<Personal> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Personal agregar(Personal personal) {
        String sql = "INSERT INTO personal (Nombres, Apellidos, Cargo, NombreUsuario, Contrasenia, Disponibilidad) values (?, ?, ?, ?, ?, ?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, personal.getNombres());
            ps.setString(2, personal.getApellidos());
            ps.setString(3, personal.getCargo());
            ps.setString(4, personal.getNomUsuario());
            ps.setString(5, personal.getContrasenia());
            ps.setBoolean(6, personal.isDisponibilidad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return personal;
    }

    @Override
    public int actualizar(Personal personal) {
        String sql = "UPDATE personal SET Nombres = ?, Apellidos = ?, Cargo = ?, NombreUsuario = ?, Contrasenia = ?, Disponibilidad = ? WHERE Id = ?";
         int filasActualizadas = 0;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, personal.getNombres());
            ps.setString(2, personal.getApellidos());
            ps.setString(3, personal.getCargo());
            ps.setString(4, personal.getNomUsuario());
            ps.setString(5, personal.getContrasenia());
            ps.setBoolean(6, personal.isDisponibilidad());
            ps.setInt(7, personal.getId());
           filasActualizadas=  ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return filasActualizadas;
    }

    @Override
    public void borrar(Personal personal) {
        String sql = "DELETE FROM personal WHERE Id = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, personal.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Personal> listarTodos() {
        List<Personal> datos = new ArrayList<>();
        String sql = "SELECT * FROM personal";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String nombres = rs.getString("Nombres");
                String apellidos = rs.getString("Apellidos");
                String cargo = rs.getString("Cargo");
                String nomUsuario = rs.getString("NombreUsuario");
                String contrasenia = rs.getString("Contrasenia");
                boolean disponibilidad = rs.getBoolean("Disponibilidad");

                Personal p = new Personal(id, nombres, apellidos, cargo, nomUsuario, contrasenia, disponibilidad);
                p.setId(id);

                datos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return datos;
    }
    

   

    @Override
    public Personal buscarPorID(int id) {
        Personal personal = null;
        String sql = "SELECT * FROM personal WHERE Id = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                personal = new Personal();
                personal.setId(rs.getInt("Id"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setNomUsuario(rs.getString("NombreUsuario"));
                personal.setContrasenia(rs.getString("Contrasenia"));
                personal.setDisponibilidad(rs.getBoolean("Disponibilidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return personal;
    }

    public Personal obtenerPersonalPorUsuarioContraseña(String usuario, String contrasenia) {
        Personal personal = null;
        String sql = "SELECT * FROM personal WHERE NombreUsuario = ? AND Contrasenia = ?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();
            if (rs.next()) {
                personal = new Personal();
                personal.setId(rs.getInt("Id"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setNomUsuario(rs.getString("NombreUsuario"));
                personal.setContrasenia(rs.getString("Contrasenia"));
                personal.setDisponibilidad(rs.getBoolean("Disponibilidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return personal;
    }
     

    public void closeResources() {
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
