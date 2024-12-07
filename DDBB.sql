CREATE TABLE Paciente (
	historia_clinica INTEGER NOT NULL,
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombre TEXT(255) NOT NULL,
	apellidos TEXT(255) NOT NULL,
	dni TEXT(9) NOT NULL,
	direccion TEXT,
	fecha_nacimiento TEXT(255) NOT NULL,
	telefono TEXT,
	correo_electronico TEXT(255)
);

CREATE TABLE Doctor (
	numero_colegiado INTEGER NOT NULL,
	nombre TEXT(255) NOT NULL,
	apellidos TEXT(255) NOT NULL,
	especialidad TEXT(255) NOT NULL,
	telefono TEXT,
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
);

CREATE TABLE Cita_medica (
	fecha TEXT(255) NOT NULL,
	motivo_consulta TEXT(255) NOT NULL,
	presencial NUMERIC NOT NULL,
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	id_doctor INTEGER NOT NULL,
	id_paciente INTEGER NOT NULL,
	CONSTRAINT Citas_medicas_Paciente_FK FOREIGN KEY (id_paciente) REFERENCES Paciente(id)
	CONSTRAINT Citas_medicas_Doctor_FK FOREIGN KEY (id_doctor) REFERENCES Doctor(id)
);

CREATE TABLE Receta (
	fecha TEXT(255) NOT NULL,
	nombre TEXT(255) NOT NULL,
	dosis TEXT NOT NULL,
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
);

CREATE TABLE Consulta (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	diagnostico TEXT(255) NOT NULL,
	tratamiento TEXT(255) NOT NULL,
	id_cita_medica INTEGER NOT NULL,
	id_receta INTEGER NOT NULL,
	CONSTRAINT Consulta_Cita_medica_FK FOREIGN KEY (id_cita_medica) REFERENCES Cita_medica(id),
	CONSTRAINT Consulta_Receta_FK FOREIGN KEY (id_receta) REFERENCES Receta(id)
);

