package org.example.modelos;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileOutputStream;

public class PdfReportGenerator {
    Connection con;

    public PdfReportGenerator(Connection con) {
        this.con = con;
    }

    public static void generarInforme() {
        String url = "jdbc:sqlite:C:\\Users\\a23albertogc\\Desktop\\BARBERIA\\Barber";
        String usuario = "";
        String contraseña = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {

            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(new FileOutputStream("informe_completo.pdf"));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título del informe
            Paragraph titulo = new Paragraph("Informe Completo de la Base de Datos")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(titulo);

            // TABLA: Pacientes
            document.add(new Paragraph("Pacientes").setBold().setFontSize(14));
            Table tablaPacientes = new Table(7);  // Columnas correspondientes
            tablaPacientes.addCell("ID");
            tablaPacientes.addCell("Historia Clínica");
            tablaPacientes.addCell("Nombre");
            tablaPacientes.addCell("Apellidos");
            tablaPacientes.addCell("DNI");
            tablaPacientes.addCell("Fecha de Nacimiento");
            tablaPacientes.addCell("Teléfono");

            String sqlPaciente = "SELECT id, historia_clinica, nombre, apellidos, dni, fecha_nacimiento, telefono FROM Paciente";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsPaciente = stmt.executeQuery(sqlPaciente)) {

                while (rsPaciente.next()) {
                    tablaPacientes.addCell(String.valueOf(rsPaciente.getInt("id")));
                    tablaPacientes.addCell(String.valueOf(rsPaciente.getInt("historia_clinica")));
                    tablaPacientes.addCell(rsPaciente.getString("nombre"));
                    tablaPacientes.addCell(rsPaciente.getString("apellidos"));
                    tablaPacientes.addCell(rsPaciente.getString("dni"));
                    tablaPacientes.addCell(rsPaciente.getString("fecha_nacimiento"));
                    tablaPacientes.addCell(rsPaciente.getString("telefono"));
                }
            }
            document.add(tablaPacientes);

            // TABLA: Doctores
            document.add(new Paragraph("Doctores").setBold().setFontSize(14));
            Table tablaDoctores = new Table(5);  // Columnas correspondientes
            tablaDoctores.addCell("ID");
            tablaDoctores.addCell("Número Colegiado");
            tablaDoctores.addCell("Nombre");
            tablaDoctores.addCell("Apellidos");
            tablaDoctores.addCell("Especialidad");

            String sqlDoctor = "SELECT id, numero_colegiado, nombre, apellidos, especialidad FROM Doctor";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsDoctor = stmt.executeQuery(sqlDoctor)) {

                while (rsDoctor.next()) {
                    tablaDoctores.addCell(String.valueOf(rsDoctor.getInt("id")));
                    tablaDoctores.addCell(String.valueOf(rsDoctor.getInt("numero_colegiado")));
                    tablaDoctores.addCell(rsDoctor.getString("nombre"));
                    tablaDoctores.addCell(rsDoctor.getString("apellidos"));
                    tablaDoctores.addCell(rsDoctor.getString("especialidad"));
                }
            }
            document.add(tablaDoctores);

            // TABLA: Citas médicas
            document.add(new Paragraph("Citas Médicas").setBold().setFontSize(14));
            Table tablaCitas = new Table(6);  // Columnas correspondientes
            tablaCitas.addCell("ID");
            tablaCitas.addCell("Fecha");
            tablaCitas.addCell("Motivo Consulta");
            tablaCitas.addCell("Presencial");
            tablaCitas.addCell("ID Paciente");
            tablaCitas.addCell("ID Doctor");

            String sqlCitas = "SELECT id, fecha, motivo_consulta, presencial, id_paciente, id_doctor FROM Cita_medica";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsCitas = stmt.executeQuery(sqlCitas)) {

                while (rsCitas.next()) {
                    tablaCitas.addCell(String.valueOf(rsCitas.getInt("id")));
                    tablaCitas.addCell(rsCitas.getString("fecha"));
                    tablaCitas.addCell(rsCitas.getString("motivo_consulta"));
                    tablaCitas.addCell(rsCitas.getString("presencial").equals("1") ? "Sí" : "No");
                    tablaCitas.addCell(String.valueOf(rsCitas.getInt("id_paciente")));
                    tablaCitas.addCell(String.valueOf(rsCitas.getInt("id_doctor")));
                }
            }
            document.add(tablaCitas);

            // TABLA: Consultas
            document.add(new Paragraph("Consultas").setBold().setFontSize(14));
            Table tablaConsultas = new Table(5);  // Columnas correspondientes
            tablaConsultas.addCell("ID");
            tablaConsultas.addCell("Diagnóstico");
            tablaConsultas.addCell("Tratamiento");
            tablaConsultas.addCell("ID Cita Médica");
            tablaConsultas.addCell("ID Receta");

            String sqlConsultas = "SELECT id, diagnostico, tratamiento, id_cita_medica, id_receta FROM Consulta";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsConsultas = stmt.executeQuery(sqlConsultas)) {

                while (rsConsultas.next()) {
                    tablaConsultas.addCell(String.valueOf(rsConsultas.getInt("id")));
                    tablaConsultas.addCell(rsConsultas.getString("diagnostico"));
                    tablaConsultas.addCell(rsConsultas.getString("tratamiento"));
                    tablaConsultas.addCell(String.valueOf(rsConsultas.getInt("id_cita_medica")));
                    tablaConsultas.addCell(String.valueOf(rsConsultas.getInt("id_receta")));
                }
            }
            document.add(tablaConsultas);

            // TABLA: Recetas
            document.add(new Paragraph("Recetas").setBold().setFontSize(14));
            Table tablaRecetas = new Table(3);  // Columnas correspondientes
            tablaRecetas.addCell("ID");
            tablaRecetas.addCell("Medicamento");
            tablaRecetas.addCell("Dosis");

            String sqlRecetas = "SELECT id, nombre, dosis FROM Receta";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsRecetas = stmt.executeQuery(sqlRecetas)) {

                while (rsRecetas.next()) {
                    tablaRecetas.addCell(String.valueOf(rsRecetas.getInt("id")));
                    tablaRecetas.addCell(rsRecetas.getString("nombre"));
                    tablaRecetas.addCell(rsRecetas.getString("dosis"));
                }
            }
            document.add(tablaRecetas);

            // Cerrar el documento
            document.close();

            System.out.println("Informe completo generado correctamente!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
