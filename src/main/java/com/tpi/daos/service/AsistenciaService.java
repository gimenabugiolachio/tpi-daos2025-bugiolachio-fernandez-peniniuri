package com.tpi.daos.service;

import com.tpi.daos.entity.Asistencia;

import java.util.Optional;

public interface AsistenciaService {
    Optional<Asistencia> getAsistenciaById (Long id);
}
