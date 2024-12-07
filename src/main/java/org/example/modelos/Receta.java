package org.example.modelos;

import java.util.Objects;

public class Receta {
    int id;
    String fecha;
    String nombre;
    String dosis;

    public Receta(){};

    public Receta(String fecha, String nombre, String dosis) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.dosis = dosis;

    }

    public Receta(int id, String fecha, String nombre, String dosis) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.dosis = dosis;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return id == receta.id && Objects.equals(fecha, receta.fecha) && Objects.equals(nombre, receta.nombre) && Objects.equals(dosis, receta.dosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, nombre, dosis);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dosis='" + dosis + '\'' +
                '}';
    }

}
