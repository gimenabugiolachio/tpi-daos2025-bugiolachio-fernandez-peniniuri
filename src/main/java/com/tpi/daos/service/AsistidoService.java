package com.tpi.daos.service;

import com.tpi.daos.dto.AsistidoDTO;
import com.tpi.daos.entity.Asistido;

import java.util.List;
import java.util.Optional;

public interface AsistidoService {
    List<Asistido> getAsistidos();

    Optional<Asistido> getAsistidoById(Long idAsistido);

    void deleteAsistido(Long id);

    void createAsistido(AsistidoDTO asistido);

    void updateAsistido(AsistidoDTO asistido);

    Optional<Asistido> findByNombre(String nombre);

    Optional<Asistido> findByDni(String dni);
}
