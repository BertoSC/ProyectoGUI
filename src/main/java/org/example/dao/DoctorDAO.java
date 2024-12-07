package org.example.dao;

import org.example.DAO;
import org.example.modelos.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements DAO<Doctor> {
    private Connection con;

    public DoctorDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctores = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getInt("numero_colegiado"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"));
                doctores.add(doctor);
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        return doctores;
    }

    @Override
    public boolean add(Doctor object) {
        String sql = "INSERT INTO Doctor (numero_colegiado, nombre, apellidos, especialidad, telefono) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, object.getNumeroColegiado());
            ps.setString(2, object.getNombre());
            ps.setString(3, object.getApellidos());
            ps.setString(4, object.getEspecialidad());
            ps.setString(5, object.getTelefono());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Doctor WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean update(Doctor object) {
        String sql = "UPDATE Doctor SET numero_colegiado = ?, nombre = ?, apellidos = ?, especialidad = ?, telefono = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, object.getNumeroColegiado());
            ps.setString(2, object.getNombre());
            ps.setString(3, object.getApellidos());
            ps.setString(4, object.getEspecialidad());
            ps.setString(5, object.getTelefono());
            ps.setInt(6, object.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e);

            return false;
        }
    }
}
