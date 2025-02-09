package org.example;

import org.example.modelos.PdfReportGenerator;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Cambiar la URL de la base de datos cuando la crees con los scripts
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\DI\\centromedico");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana(con).setVisible(true);
            }
        });
    }
}