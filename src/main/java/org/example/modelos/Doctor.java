package org.example.modelos;

import java.util.Objects;

public class Doctor {
    private int id;
    private int numeroColegiado;
    private String nombre;
    private String apellidos;
    private String especialidad;
    private String telefono;

    public Doctor(int id, int numeroColegiado, String nombre, String apellidos, String especialidad, String telefono) {
        this.id = id;
        this.numeroColegiado = numeroColegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public Doctor(int numeroColegiado, String nombre, String apellidos, String especialidad, String telefono) {
        this.numeroColegiado = numeroColegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && numeroColegiado == doctor.numeroColegiado && Objects.equals(nombre, doctor.nombre) && Objects.equals(apellidos, doctor.apellidos) && Objects.equals(especialidad, doctor.especialidad) && Objects.equals(telefono, doctor.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroColegiado, nombre, apellidos, especialidad, telefono);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", numeroColegiado=" + numeroColegiado +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", telefono='" + telefono + '\'' +
                "} \n";
    }
}
