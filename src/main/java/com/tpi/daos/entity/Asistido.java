package com.tpi.daos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Asistido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String domicilio;
    private LocalDate fechaNacimiento;
    private int edadRegistro;
    @ManyToOne
    private Ciudad ciudad;

    public Asistido() {
    }

    public Asistido(Long id, String dni, String nombre, String domicilio, Ciudad idCiudad, LocalDate fechaNacimiento, int edadRegistro) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.ciudad = idCiudad;
        this.fechaNacimiento = fechaNacimiento;
        this.edadRegistro = edadRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdadRegistro(int edadRegistro) {
        return this.edadRegistro;
    }

    public void setEdadRegistro(int edadRegistro) {
        this.edadRegistro = edadRegistro;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
