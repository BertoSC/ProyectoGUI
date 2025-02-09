package org.example;

import org.example.dao.*;
import org.example.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

public class Ventana extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection con;

    public Ventana(Connection con) {
        this.con = con;

        setTitle("Centro Médico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel(new GridLayout(2, 5));
        JButton btnMostrarDoctores = new JButton("Mostrar Doctores");
        JButton btnMostrarPacientes = new JButton("Mostrar Pacientes");
        JButton btnMostrarConsultas = new JButton("Mostrar Consultas");
        JButton btnMostrarRecetas = new JButton("Mostrar Recetas");
        JButton btnMostrarCitas = new JButton("Mostrar Citas");

        JButton btnAddDoctor = new JButton("Añadir Doctor");
        JButton btnAddPaciente = new JButton("Añadir Paciente");
        JButton btnAddConsulta = new JButton("Añadir Consulta");
        JButton btnAddReceta = new JButton("Añadir Receta");
        JButton btnAddCita = new JButton("Añadir Cita");

        buttonPanel.add(btnMostrarDoctores);
        buttonPanel.add(btnMostrarPacientes);
        buttonPanel.add(btnMostrarConsultas);
        buttonPanel.add(btnMostrarRecetas);
        buttonPanel.add(btnMostrarCitas);

        buttonPanel.add(btnAddDoctor);
        buttonPanel.add(btnAddPaciente);
        buttonPanel.add(btnAddConsulta);
        buttonPanel.add(btnAddReceta);
        buttonPanel.add(btnAddCita);

        add(buttonPanel, BorderLayout.NORTH);
        JPanel buttonPanelPDF = new JPanel(new GridLayout(1, 1));
        JButton descargarPDF = new JButton("DESCARGAR INFORME DE DATOS");
        buttonPanelPDF.add(descargarPDF);
        add(buttonPanelPDF, BorderLayout.SOUTH);

        descargarPDF.addActionListener(e -> {
            PdfReportGenerator.generarInforme();
        });



        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(getColumnClass(column).equals(JButton.class));
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        btnMostrarDoctores.addActionListener(e -> mostrarDoctores());
        btnMostrarPacientes.addActionListener(e -> mostrarPacientes());
        btnMostrarConsultas.addActionListener(e -> mostrarConsultas());
        btnMostrarRecetas.addActionListener(e -> mostrarRecetas());
        btnMostrarCitas.addActionListener(e -> mostrarCitas());

        btnAddDoctor.addActionListener(e -> mostrarFormularioDoctor());
        btnAddPaciente.addActionListener(e -> mostrarFormularioPaciente());
        btnAddConsulta.addActionListener(e -> mostrarFormularioConsulta());
        btnAddReceta.addActionListener(e -> mostrarFormularioReceta());
        btnAddCita.addActionListener(e -> mostrarFormularioCita());
    }

    private void mostrarFormularioDoctor() {
        JDialog dialog = new JDialog(this, "Añadir Doctor", true);
        dialog.setLayout(new GridLayout(6, 2));
        dialog.setSize(400, 300);

        JTextField txtNumeroColegiado = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtEspecialidad = new JTextField();
        JTextField txtTelefono = new JTextField();

        dialog.add(new JLabel("Número Colegiado:"));
        dialog.add(txtNumeroColegiado);
        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("Especialidad:"));
        dialog.add(txtEspecialidad);
        dialog.add(new JLabel("Teléfono:"));
        dialog.add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            int numeroColegiado = Integer.parseInt(txtNumeroColegiado.getText());
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String especialidad = txtEspecialidad.getText();
            String telefono = txtTelefono.getText();

            Doctor doctor = new Doctor(0, numeroColegiado, nombre, apellidos, especialidad, telefono);
            new DoctorDAO(con).add(doctor);

            JOptionPane.showMessageDialog(dialog, "Doctor añadido correctamente");
            dialog.dispose();
            mostrarDoctores();
        });

        dialog.add(new JLabel());
        dialog.add(btnGuardar);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }

    private void mostrarFormularioPaciente() {
        JDialog dialog = new JDialog(this, "Añadir Paciente", true);
        dialog.setLayout(new GridLayout(9, 2));
        dialog.setSize(400, 400);

        JTextField txtHistoriaClinica = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtFechaNacimiento = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreoElectronico = new JTextField();

        dialog.add(new JLabel("Historia Clínica:"));
        dialog.add(txtHistoriaClinica);
        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("DNI:"));
        dialog.add(txtDni);
        dialog.add(new JLabel("Dirección:"));
        dialog.add(txtDireccion);
        dialog.add(new JLabel("Fecha de Nacimiento:"));
        dialog.add(txtFechaNacimiento);
        dialog.add(new JLabel("Teléfono:"));
        dialog.add(txtTelefono);
        dialog.add(new JLabel("Correo Electrónico:"));
        dialog.add(txtCorreoElectronico);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            int historiaClinica = Integer.parseInt(txtHistoriaClinica.getText());
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String dni = txtDni.getText();
            String direccion = txtDireccion.getText();
            String fechaNacimiento = txtFechaNacimiento.getText();
            String telefono = txtTelefono.getText();
            String correoElectronico = txtCorreoElectronico.getText();

            Paciente paciente = new Paciente(0, historiaClinica, nombre, apellidos, dni, direccion, fechaNacimiento, telefono, correoElectronico);
            new PacienteDAO(con).add(paciente);

            JOptionPane.showMessageDialog(dialog, "Paciente añadido correctamente");
            dialog.dispose();
            mostrarPacientes();
        });

        dialog.add(new JLabel());
        dialog.add(btnGuardar);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void mostrarFormularioConsulta() {
        JDialog dialog = new JDialog(this, "Añadir Consulta", true);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        dialog.setSize(400, 400);

        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        JTextField txtDiagnostico = new JTextField();

        JLabel lblTratamiento = new JLabel("Tratamiento:");
        JTextField txtTratamiento = new JTextField();

        JLabel lblIdCita = new JLabel("ID Cita:");
        JTextField txtIdCita = new JTextField();

        JLabel lblIdReceta = new JLabel("ID Receta:");
        JTextField txtIdReceta = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                String diagnostico = txtDiagnostico.getText();
                String tratamiento = txtTratamiento.getText();
                int idCita = Integer.parseInt(txtIdCita.getText());
                int idReceta = Integer.parseInt(txtIdReceta.getText());

                Consulta consulta = new Consulta(0, diagnostico, tratamiento, idCita, idReceta);
                new ConsultaDAO(con).add(consulta);

                JOptionPane.showMessageDialog(dialog, "Consulta añadida correctamente.");
                dialog.dispose();
                mostrarConsultas(); // Actualizar tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al añadir consulta: " + ex.getMessage());
            }
        });

        dialog.add(lblDiagnostico);
        dialog.add(txtDiagnostico);
        dialog.add(lblTratamiento);
        dialog.add(txtTratamiento);
        dialog.add(lblIdCita);
        dialog.add(txtIdCita);
        dialog.add(lblIdReceta);
        dialog.add(txtIdReceta);
        dialog.add(new JLabel()); // Espacio vacío
        dialog.add(btnGuardar);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void mostrarFormularioReceta() {
        JDialog dialog = new JDialog(this, "Añadir Receta", true);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));
        dialog.setSize(400, 400);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField txtFecha = new JTextField();

        JLabel lblNombre = new JLabel("Nombre del Medicamento:");
        JTextField txtNombre = new JTextField();

        JLabel lblDosis = new JLabel("Dosis:");
        JTextField txtDosis = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                String fecha = txtFecha.getText();
                String nombre = txtNombre.getText();
                String dosis = txtDosis.getText();

                Receta receta = new Receta(0, fecha, nombre, dosis);
                new RecetaDAO(con).add(receta);

                JOptionPane.showMessageDialog(dialog, "Receta añadida correctamente.");
                dialog.dispose();
                mostrarRecetas(); // Actualizar tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al añadir receta: " + ex.getMessage());
            }
        });

        dialog.add(lblFecha);
        dialog.add(txtFecha);
        dialog.add(lblNombre);
        dialog.add(txtNombre);
        dialog.add(lblDosis);
        dialog.add(txtDosis);
        dialog.add(new JLabel()); // Espacio vacío
        dialog.add(btnGuardar);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void mostrarFormularioCita() {
        JDialog dialog = new JDialog(this, "Añadir Cita Médica", true);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        dialog.setSize(400, 400);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField txtFecha = new JTextField();

        JLabel lblMotivoConsulta = new JLabel("Motivo de Consulta:");
        JTextField txtMotivoConsulta = new JTextField();

        JLabel lblPresencial = new JLabel("Presencial (1 para sí, 0 para no):");
        JTextField txtPresencial = new JTextField();

        JLabel lblIdDoctor = new JLabel("ID Doctor:");
        JTextField txtIdDoctor = new JTextField();

        JLabel lblIdPaciente = new JLabel("ID Paciente:");
        JTextField txtIdPaciente = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                String fecha = txtFecha.getText();
                String motivoConsulta = txtMotivoConsulta.getText();
                int presencial = Integer.parseInt(txtPresencial.getText());
                int idDoctor = Integer.parseInt(txtIdDoctor.getText());
                int idPaciente = Integer.parseInt(txtIdPaciente.getText());

                CitaMedica cita = new CitaMedica(fecha, motivoConsulta, presencial, 0, idDoctor, idPaciente);
                new CitaMedicaDAO(con).add(cita);

                JOptionPane.showMessageDialog(dialog, "Cita Médica añadida correctamente.");
                dialog.dispose();
                mostrarCitas(); // Actualizar tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al añadir cita médica: " + ex.getMessage());
            }
        });

        dialog.add(lblFecha);
        dialog.add(txtFecha);
        dialog.add(lblMotivoConsulta);
        dialog.add(txtMotivoConsulta);
        dialog.add(lblPresencial);
        dialog.add(txtPresencial);
        dialog.add(lblIdDoctor);
        dialog.add(txtIdDoctor);
        dialog.add(lblIdPaciente);
        dialog.add(txtIdPaciente);
        dialog.add(new JLabel());
        dialog.add(btnGuardar);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }


    private void mostrarDoctores() {
        DoctorDAO doctorDAO = new DoctorDAO(con);
        List<Doctor> doctores = doctorDAO.getAll();

        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Número Colegiado", "Nombre", "Apellidos", "Especialidad", "Teléfono", "", ""});

        for (Doctor doctor : doctores) {
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> eliminarDoctor(doctor.getId()));

            JButton btnActualizar = new JButton("Actualizar");
            btnActualizar.addActionListener(e -> actualizarDoctor(doctor.getId()));

            tableModel.addRow(new Object[]{doctor.getId(), doctor.getNumeroColegiado(), doctor.getNombre(),
                    doctor.getApellidos(), doctor.getEspecialidad(), doctor.getTelefono(), btnEliminar, btnActualizar});
        }

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());

        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
    }

    private void mostrarPacientes() {
        PacienteDAO pacienteDAO = new PacienteDAO(con);
        List<Paciente> pacientes = pacienteDAO.getAll();


        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{
                "ID", "Historia Clínica", "Nombre", "Apellidos", "DNI",
                "Dirección", "Fecha Nacimiento", "Teléfono", "Correo Electrónico", "", ""});

        for (Paciente paciente : pacientes) {
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> eliminarPaciente(paciente.getId()));

            JButton btnActualizar = new JButton("Actualizar");
            btnActualizar.addActionListener(e -> actualizarPaciente(paciente.getId()));

            tableModel.addRow(new Object[]{
                    paciente.getId(), paciente.getHistoriaClinica(), paciente.getNombre(),
                    paciente.getApellidos(), paciente.getDni(), paciente.getDireccion(),
                    paciente.getFechaNacimiento(), paciente.getTelefono(), paciente.getCorreoElectronico(),
                    btnEliminar, btnActualizar});
        }


        table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor());

        table.getColumnModel().getColumn(10).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(10).setCellEditor(new ButtonEditor());
    }



    private void mostrarConsultas() {
        ConsultaDAO consultaDAO = new ConsultaDAO(con);
        List<Consulta> consultas = consultaDAO.getAll();

        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Diagnóstico", "Tratamiento", "ID Cita", "ID Receta", "", ""});

        for (Consulta consulta : consultas) {
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> eliminarConsulta(consulta.getId()));
            JButton btnActualizar = new JButton("Actualizar");
            btnActualizar.addActionListener(e -> actualizarConsulta(consulta.getId()));
            tableModel.addRow(new Object[]{consulta.getId(), consulta.getDiagnostico(), consulta.getTratamiento(),
                    consulta.getIdCita(), consulta.getIdReceta(), btnEliminar, btnActualizar});
        }

        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
    }

    private void mostrarRecetas() {
        RecetaDAO recetaDAO = new RecetaDAO(con);
        List<Receta> recetas = recetaDAO.getAll();

        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Fecha", "Nombre", "Dosis", "", ""});

        for (Receta receta : recetas) {
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> eliminarReceta(receta.getId()));
            JButton btnActualizar = new JButton("Actualizar");
            btnActualizar.addActionListener(e -> actualizarReceta(receta.getId()));
            tableModel.addRow(new Object[]{receta.getId(), receta.getFecha(), receta.getNombre(), receta.getDosis(), btnEliminar, btnActualizar});
        }

        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());
    }

    private void mostrarCitas() {
        CitaMedicaDAO citaMedicaDAO = new CitaMedicaDAO(con);
        List<CitaMedica> citas = citaMedicaDAO.getAll();

        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"Fecha", "Motivo Consulta", "Presencial", "ID", "ID Doctor", "ID Paciente", "", ""});

        for (CitaMedica cita : citas) {
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> eliminarCita(cita.getId()));
            JButton btnActualizar = new JButton("Actualizar");
            btnActualizar.addActionListener(e -> actualizarCita(cita.getId()));

            tableModel.addRow(new Object[]{cita.getFecha(), cita.getMotivoConsulta(),
                    cita.getPresencial(), cita.getId(), cita.getIdDoctor(), cita.getIdPaciente(), btnEliminar, btnActualizar});
        }

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
    }

    private void eliminarDoctor(int id) {
        new DoctorDAO(con).delete(id);
        mostrarDoctores();
    }

    private void eliminarPaciente(int id) {
        new PacienteDAO(con).delete(id);
        mostrarPacientes();
    }

    private void eliminarConsulta(int id) {
        new ConsultaDAO(con).delete(id);
        mostrarConsultas();
    }

    private void eliminarReceta(int id) {
        new RecetaDAO(con).delete(id);
        mostrarRecetas();
    }

    private void eliminarCita(int id) {
        new CitaMedicaDAO(con).delete(id);
        mostrarCitas();
    }


    private void actualizarDoctor(int id) {
        int row = table.getSelectedRow();
        if (row != -1) {
            int numeroColegiado = (int)tableModel.getValueAt(row, 1);
            String nombre = (String) tableModel.getValueAt(row, 2);
            String apellidos = (String) tableModel.getValueAt(row, 3);
            String especialidad = (String) tableModel.getValueAt(row, 4);
            String telefono = (String) tableModel.getValueAt(row, 5);

            Doctor doctor = new Doctor(id, numeroColegiado, nombre, apellidos, especialidad, telefono);
            new DoctorDAO(con).update(doctor);

            JOptionPane.showMessageDialog(this, "Doctor actualizado correctamente");
            mostrarDoctores();
        }
    }



    private void actualizarPaciente(int id) {
        int row = table.getSelectedRow();
        if (row != -1) {
            int historiaClinica = (int) tableModel.getValueAt(row, 1);
            String nombre = (String) tableModel.getValueAt(row, 2);
            String apellidos = (String) tableModel.getValueAt(row, 3);
            String dni = (String) tableModel.getValueAt(row, 4);
            String direccion = (String) tableModel.getValueAt(row, 5);
            String fechaNacimiento = (String) tableModel.getValueAt(row, 6); // Suponiendo que se guarda como String
            String telefono = (String) tableModel.getValueAt(row, 7);
            String correoElectronico = (String) tableModel.getValueAt(row, 8);


            Paciente paciente = new Paciente(id, historiaClinica, nombre, apellidos, dni, direccion, fechaNacimiento, telefono, correoElectronico);


            new PacienteDAO(con).update(paciente);

            JOptionPane.showMessageDialog(this, "Paciente actualizado correctamente");
            mostrarPacientes();
        }
    }

    private void actualizarConsulta(int id) {
        int row = table.getSelectedRow();
        if (row != -1) {
            String diagnostico = (String) tableModel.getValueAt(row, 1);
            String tratamiento = (String) tableModel.getValueAt(row, 2);
            int idCita = Integer.parseInt(tableModel.getValueAt(row, 3).toString());
            int idReceta = Integer.parseInt(tableModel.getValueAt(row, 4).toString());

            Consulta consulta = new Consulta(id, diagnostico, tratamiento, idCita, idReceta);
            new ConsultaDAO(con).update(consulta);

            JOptionPane.showMessageDialog(this, "Consulta actualizada correctamente");
            mostrarConsultas();
        }
    }

    private void actualizarReceta(int id) {
        int row = table.getSelectedRow();
        if (row != -1) {
            String fecha = (String) tableModel.getValueAt(row, 1);
            String nombre = (String) tableModel.getValueAt(row, 2);
            String dosis = (String) tableModel.getValueAt(row, 3);

            Receta receta = new Receta(id, fecha, nombre, dosis);
            new RecetaDAO(con).update(receta);

            JOptionPane.showMessageDialog(this, "Receta actualizada correctamente");
            mostrarRecetas();
        }
    }

    private void actualizarCita(int id) {
        int row = table.getSelectedRow();
        if (row != -1) {
            String fecha = (String) tableModel.getValueAt(row, 0);
            String motivoConsulta = (String) tableModel.getValueAt(row, 1);
            int presencial =Integer.parseInt((tableModel.getValueAt(row, 2).toString()));
            int idDoctor = Integer.parseInt(tableModel.getValueAt(row, 4).toString());
            int idPaciente = Integer.parseInt(tableModel.getValueAt(row, 5).toString());

            CitaMedica cita = new CitaMedica(fecha, motivoConsulta, presencial, id, idDoctor, idPaciente);
            new CitaMedicaDAO(con).update(cita);

            JOptionPane.showMessageDialog(this, "Cita actualizada correctamente");
            mostrarCitas();
        }
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JButton) {
                return (JButton) value;
            }
            return this;
        }
    }

    private static class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public ButtonEditor() {
            button = new JButton();
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof JButton) {
                return (JButton) value;
            }
            return null;
        }

        @Override
        public Object getCellEditorValue() {
            return button;
        }
    }
}
