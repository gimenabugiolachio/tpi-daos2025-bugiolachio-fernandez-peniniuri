package com.tpi.daos.dto;

import com.tpi.daos.entity.Ciudad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aspectj.lang.annotation.RequiredTypes;

import java.time.LocalDate;

public class AsistidoDTO {
    @Positive(message = "El DNI debe ser un número entero positivo")
    private String dni; // Si lo usás como texto, no podés validar con @Positive (solo números)

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String domicilio;

    private LocalDate fechaNacimiento;

    @NotNull(message = "La edad al momento del registro debe ser un número positivo")
    private Integer edadRegistro;

    @NotNull(message = "Ciudad no puede ser nula")
    @Positive(message = "El ID de la ciudad debe ser un número positivo")
    private Long idCiudad;

    private Ciudad ciudad;

    public AsistidoDTO() {
    }

    public AsistidoDTO(String dni, @NotBlank String nombre, String domicilio, LocalDate fechaNacimiento, int edadRegistro, Long idCiudad) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.edadRegistro = edadRegistro;
        this.idCiudad = idCiudad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @NotBlank
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
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

    public int getEdadRegistro() {
        return edadRegistro;
    }

    public void setEdadRegistro(int edadRegistro) {
        this.edadRegistro = edadRegistro;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setEdadRegistro(Integer edadRegistro) {
        this.edadRegistro = edadRegistro;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
