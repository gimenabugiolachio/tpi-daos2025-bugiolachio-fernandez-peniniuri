package com.tpi.daos.repository;

import com.tpi.daos.entity.Asistido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsistidoRepository extends JpaRepository<Asistido, Long> {
    Optional<Asistido> findByNombre(String nombre);

    Optional<Asistido> findByDni(String dni);
}
