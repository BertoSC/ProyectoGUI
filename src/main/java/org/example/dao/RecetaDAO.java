package org.example.dao;

import org.example.DAO;
import org.example.modelos.Receta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecetaDAO implements DAO<Receta> {
    Connection con;

    public RecetaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List getAll() {
        String consulta = "select * from Receta";
        List<Receta> listaReceta = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Receta rec = new Receta(rs.getInt("id"), rs.getString("fecha"), rs.getString("nombre"), rs.getString("dosis"));
                listaReceta.add(rec);
            }
            return listaReceta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Receta receta) {
        String consulta = "INSERT INTO Receta (fecha, nombre, dosis) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, receta.getFecha());
            ps.setString(2, receta.getNombre());
            ps.setString(3, receta.getDosis());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        String consulta = "DELETE FROM Receta WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Receta receta) {
        String consulta = "UPDATE Receta SET fecha = ?, nombre = ?, dosis = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, receta.getFecha());
            ps.setString(2, receta.getNombre());
            ps.setString(3, receta.getDosis());
            ps.setInt(4, receta.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


