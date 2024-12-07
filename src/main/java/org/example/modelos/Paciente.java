package org.example.modelos;

public class Paciente {
    private int id;
    private int historiaClinica;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String fechaNacimiento;
    private String telefono;
    private String correoElectronico;

    public Paciente(int id, int historiaClinica, String nombre, String apellidos, String dni,
                    String direccion, String fechaNacimiento, String telefono, String correoElectronico) {
        this.id = id;
        this.historiaClinica = historiaClinica;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Paciente(int historiaClinica, String nombre, String apellidos, String dni,
                    String direccion, String fechaNacimiento, String telefono, String correoElectronico) {
        this.historiaClinica = historiaClinica;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(int historiaClinica) {
        this.historiaClinica = historiaClinica;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
