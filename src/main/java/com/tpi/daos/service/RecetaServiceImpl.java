package com.tpi.daos.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.daos.entity.Receta;
import com.tpi.daos.repository.RecetaRepository;

@Service
@Transactional
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository repo;

    public RecetaServiceImpl(RecetaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Receta crear(Receta receta) {
        validarDatos(receta);

        if (repo.existsByNombreIgnoreCase(receta.getNombre())) {
            throw new IllegalArgumentException("Ya existe una receta con ese nombre");
        }

        receta.setId(null);

        return repo.save(receta);
    }

    @Override
    public Receta actualizar(Long id, Receta receta) {
        validarDatos(receta);

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

    private void validarDatos(Receta receta) {
        if (receta.getNombre() == null || receta.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la receta es obligatorio");
        }
        if (receta.getPesoRacion() == null ||
                receta.getPesoRacion().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El peso de la ración debe ser un número positivo");
        }
        if (receta.getCaloriasPorRacion() == null ||
                receta.getCaloriasPorRacion() <= 0) {
            throw new IllegalArgumentException("Las calorías por ración deben ser un entero positivo");
        }
    }
}
