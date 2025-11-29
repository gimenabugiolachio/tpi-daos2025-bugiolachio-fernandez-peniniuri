package com.tpi.daos.service;

import com.tpi.daos.entity.Racion;
import java.util.List;

public interface RacionService {
    Racion crear(Racion racion);
    Racion actualizar(Long id, Racion racion);
    void eliminar(Long id);
    Racion buscarPorId(Long id);
    List<Racion> listarTodas();
}