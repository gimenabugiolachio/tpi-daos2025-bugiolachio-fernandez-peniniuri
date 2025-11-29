package com.tpi.daos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Asistencia {
    @Id
    private Long id;
    @ManyToOne
    private Asistido asistido;
    @ManyToOne
    private Receta receta;
    private LocalDate fechaEntrega;

    public Asistencia() {
    }

    public Asistencia(Long id, Asistido asistido, Receta receta, LocalDate fechaEntrega) {
        this.id = id;
        this.asistido = asistido;
        this.receta = receta;
        this.fechaEntrega = fechaEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asistido getAsistido() {
        return asistido;
    }

    public void setAsistido(Asistido asistido) {
        this.asistido = asistido;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
