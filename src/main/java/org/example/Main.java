package org.example;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Connection con = DriverManager.getConnection("jdbc:sqlite:L:\\DesenvolvementoInterfaces\\CentroMedico");
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\DI\\centromedico");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana(con).setVisible(true);
            }
        });
    }
}