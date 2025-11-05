package com.tpi.daos.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

	@Entity
	public class Receta {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String nombre;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal pesoRacion;

	    @Column(nullable = false)
	    private Integer caloriasPorRacion;

	    // --- Getters y Setters ---

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public BigDecimal getPesoRacion() {
	        return pesoRacion;
	    }

	    public void setPesoRacion(BigDecimal pesoRacion) {
	        this.pesoRacion = pesoRacion;
	    }

	    public Integer getCaloriasPorRacion() {
	        return caloriasPorRacion;
	    }

	    public void setCaloriasPorRacion(Integer caloriasPorRacion) {
	        this.caloriasPorRacion = caloriasPorRacion;
	    }
	}


