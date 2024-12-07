package org.example.dao;

import org.example.modelos.Consulta;
import org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO implements DAO<Consulta> {
    private Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Consulta> getAll() {
        String consulta = "SELECT * FROM Consulta";
        List<Consulta> listaConsulta = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta cons = new Consulta(rs.getInt("id"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento"),
                        rs.getInt("id_cita_medica"),
                        rs.getInt("id_receta"));
                listaConsulta.add(cons);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsulta;
    }

    @Override
    public boolean add(Consulta consulta) {
        String consultaSQL = "INSERT INTO Consulta (diagnostico, tratamiento, id_cita_medica, id_receta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, consulta.getDiagnostico());
            ps.setString(2, consulta.getTratamiento());
            ps.setInt(3, consulta.getIdCita());
            ps.setInt(4, consulta.getIdReceta());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        String consultaSQL = "DELETE FROM Consulta WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Consulta consulta) {
        String consultaSQL = "UPDATE Consulta SET diagnostico = ?, tratamiento = ?, id_cita_medica = ?, id_receta = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, consulta.getDiagnostico());
            ps.setString(2, consulta.getTratamiento());
            ps.setInt(3, consulta.getIdCita());
            ps.setInt(4, consulta.getIdReceta());
            ps.setInt(5, consulta.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}