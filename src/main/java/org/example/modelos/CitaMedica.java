package org.example.modelos;

import java.util.Objects;

public class CitaMedica {
    String fecha;
    String motivoConsulta;
    int presencial; // para 1 o 0
    int id;
    int idDoctor;
    int idPaciente;

    public CitaMedica(String fecha, String motivoConsulta, int presencial, int idDoctor, int idPaciente) {
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
        this.presencial = presencial;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    public CitaMedica(String fecha, String motivoConsulta, int presencial, int id, int idDoctor, int idPaciente) {
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
        this.presencial = presencial;
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public int getPresencial() {
        return presencial;
    }

    public void setPresencial(int presencial) {
        this.presencial = presencial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaMedica that = (CitaMedica) o;
        return presencial == that.presencial && id == that.id && idDoctor == that.idDoctor && idPaciente == that.idPaciente && Objects.equals(fecha, that.fecha) && Objects.equals(motivoConsulta, that.motivoConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, motivoConsulta, presencial, id, idDoctor, idPaciente);
    }

    @Override
    public String toString() {
        return "CitaMedica{" +
                "fecha='" + fecha + '\'' +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", presencial=" + presencial +
                ", id=" + id +
                ", idDoctor=" + idDoctor +
                ", idPaciente=" + idPaciente +
                '}';
    }

}
