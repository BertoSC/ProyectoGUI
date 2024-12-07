package org.example.dao;

import org.example.modelos.CitaMedica;
import org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaMedicaDAO implements DAO<CitaMedica> {
    private Connection con;

    public CitaMedicaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<CitaMedica> getAll() {
        String consulta = "SELECT * FROM Cita_medica";
        List<CitaMedica> listaCitas = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CitaMedica cita = new CitaMedica(rs.getString("fecha"),
                        rs.getString("motivo_consulta"),
                        rs.getInt("presencial"),
                        rs.getInt("id"),
                        rs.getInt("id_doctor"),
                        rs.getInt("id_paciente"));
                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCitas;
    }

    @Override
    public boolean add(CitaMedica cita) {
        String consultaSQL = "INSERT INTO Cita_medica (fecha, motivo_consulta, presencial, id_doctor, idpaciente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, cita.getFecha());
            ps.setString(2, cita.getMotivoConsulta());
            ps.setInt(3, cita.getPresencial());
            ps.setInt(4, cita.getIdDoctor());
            ps.setInt(5, cita.getIdPaciente());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        String consultaSQL = "DELETE FROM Cita_medica WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CitaMedica cita) {
        String consultaSQL = "UPDATE Cita_medica SET fecha = ?, motivo_consulta = ?, presencial = ?, id_doctor = ?, id_paciente = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, cita.getFecha());
            ps.setString(2, cita.getMotivoConsulta());
            ps.setInt(3, cita.getPresencial());
            ps.setInt(4, cita.getIdDoctor());
            ps.setInt(5, cita.getIdPaciente());
            ps.setInt(6, cita.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}