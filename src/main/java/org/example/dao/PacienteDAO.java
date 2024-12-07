package org.example.dao;

import org.example.DAO;
import org.example.modelos.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements DAO<Paciente> {
    private Connection con;

    public PacienteDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Paciente> getAll() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Paciente paciente = new Paciente(
                        rs.getInt("id"),
                        rs.getInt("historia_clinica"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("direccion"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"));
                pacientes.add(paciente);
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        return pacientes;
    }

    @Override
    public boolean add(Paciente object) {
        String sql = "INSERT INTO Paciente (historia_clinica, nombre, apellidos, dni, direccion, fecha_nacimiento, telefono, correo_electronico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, object.getHistoriaClinica());
            ps.setString(2, object.getNombre());
            ps.setString(3, object.getApellidos());
            ps.setString(4, object.getDni());
            ps.setString(5, object.getDireccion());
            ps.setString(6, object.getFechaNacimiento());
            ps.setString(7, object.getTelefono());
            ps.setString(8, object.getCorreoElectronico());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean update(Paciente object) {
        String sql = "UPDATE Paciente SET historia_clinica = ?, nombre = ?, apellidos = ?, dni = ?, direccion = ?, fecha_nacimiento = ?, telefono = ?, correo_electronico = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, object.getHistoriaClinica());
            ps.setString(2, object.getNombre());
            ps.setString(3, object.getApellidos());
            ps.setString(4, object.getDni());
            ps.setString(5, object.getDireccion());
            ps.setString(6, object.getFechaNacimiento());
            ps.setString(7, object.getTelefono());
            ps.setString(8, object.getCorreoElectronico());
            ps.setInt(9, object.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }
}
