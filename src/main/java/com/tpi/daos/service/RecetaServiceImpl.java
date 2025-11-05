package com.tpi.daos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpi.daos.entity.Receta;
import com.tpi.daos.repository.RecetaRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecetaServiceImpl implements RecetaService {
	private final RecetaRepository repo;

    public RecetaServiceImpl(RecetaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Receta crear(Receta receta) {
        if (repo.existsByNombreIgnoreCase(receta.getNombre())) {
            throw new IllegalArgumentException("Ya existe una receta con ese nombre");
        }
        return repo.save(receta);
    }

    @Override
    public Receta actualizar(Long id, Receta receta) {
        Receta existente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        existente.setNombre(receta.getNombre());
        existente.setPesoRacion(receta.getPesoRacion());
        existente.setCaloriasPorRacion(receta.getCaloriasPorRacion());

        return repo.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Receta no encontrada");
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Receta buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Receta> listarTodas() {
        return repo.findAll();
    }
}
