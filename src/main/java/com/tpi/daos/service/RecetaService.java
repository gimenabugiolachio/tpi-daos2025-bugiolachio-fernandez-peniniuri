package com.tpi.daos.service;

import java.util.List;

import com.tpi.daos.entity.Receta;

public interface RecetaService {
	 Receta crear(Receta receta);
	 Receta actualizar(Long id, Receta receta);
	 void eliminar(Long id);
	 Receta buscarPorId(Long id);
	 List<Receta> listarTodas();
}
