package org.example.modelos;

import java.util.Objects;

public class Consulta {
    int id;
    String diagnostico;
    String tratamiento;
    int idCita;
    int idReceta;

    public Consulta(String diagnostico, String tratamiento, int idCita, int idReceta) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.idCita = idCita;
        this.idReceta = idReceta;
    }

    public Consulta(int id, String diagnostico, String tratamiento, int idCita, int idReceta) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.idCita = idCita;
        this.idReceta = idReceta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return id == consulta.id && idCita == consulta.idCita && idReceta == consulta.idReceta && Objects.equals(diagnostico, consulta.diagnostico) && Objects.equals(tratamiento, consulta.tratamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diagnostico, tratamiento, idCita, idReceta);
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", idCita=" + idCita +
                ", idReceta=" + idReceta +
                '}';
    }
}
