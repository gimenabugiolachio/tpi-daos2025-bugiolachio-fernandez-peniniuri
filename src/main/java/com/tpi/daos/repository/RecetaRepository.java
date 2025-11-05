package com.tpi.daos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.daos.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
}