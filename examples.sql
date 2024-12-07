INSERT INTO Paciente (historia_clinica, nombre, apellidos, dni, direccion, fecha_nacimiento, telefono, correo_electronico)
VALUES
(12345, 'Juan', 'Pérez', '12345678A', 'Calle Falsa 123', '1980-05-14', '600123456', 'juan.perez@example.com'),
(67890, 'María', 'Gómez', '98765432B', 'Avenida Siempre Viva 456', '1992-08-25', '650987654', 'maria.gomez@example.com');

INSERT INTO Doctor (numero_colegiado, nombre, apellidos, especialidad, telefono)
VALUES
(111222, 'Carlos', 'Fernández', 'Cardiología', '611123456'),
(333444, 'Ana', 'López', 'Dermatología', '622234567');

INSERT INTO Cita_medica (fecha, motivo_consulta, presencial, id_doctor, id_paciente)
VALUES
('2024-11-25 09:30:00', 'Dolor de cabeza', 1, 1, 1),
('2024-12-01 14:00:00', 'Revisión general', 0, 2, 2);

INSERT INTO Receta (fecha, nombre, dosis)
VALUES
('2024-11-20 08:00:00', 'Paracetamol', '500mg cada 8 horas'),
('2024-11-22 10:15:00', 'Ibuprofeno', '200mg cada 6 horas');

INSERT INTO Consulta (diagnostico, tratamiento, id_cita_medica, id_receta)
VALUES
('Migraña', 'Reposo y paracetamol', 1, 1),
('Dermatitis', 'Aplicar crema hidratante y evitar irritantes', 2, 2);
