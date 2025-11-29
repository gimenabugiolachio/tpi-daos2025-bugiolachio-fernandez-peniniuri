package com.tpi.daos.service;

import com.tpi.daos.entity.Racion;
import com.tpi.daos.entity.Receta;
import com.tpi.daos.repository.RacionRepository;
import com.tpi.daos.repository.RecetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RacionServiceImpl implements RacionService {

    private final RacionRepository repo;
    private final RecetaRepository recetaRepo;

    public RacionServiceImpl(RacionRepository repo, RecetaRepository recetaRepo) {
        this.repo = repo;
        this.recetaRepo = recetaRepo;
    }

    @Override
    public Racion crear(Racion racion) {
        // 1. Validar que la receta exista
        if (racion.getReceta() == null || racion.getReceta().getId() == null) {
            throw new IllegalArgumentException("La ración debe tener una receta asociada.");
        }
        Receta receta = recetaRepo.findById(racion.getReceta().getId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));
        racion.setReceta(receta);

        // 2. Validar fechas (Vencimiento > Preparación)
        validarFechas(racion);

        // 3. Regla de negocio: Stock Restante inicial = Stock Preparado
        if (racion.getStockPreparado() == null || racion.getStockPreparado() < 0) {
             throw new IllegalArgumentException("El stock preparado debe ser un entero positivo.");
        }
        racion.setStockRestante(racion.getStockPreparado());

        return repo.save(racion);
    }

    @Override
    public Racion actualizar(Long id, Racion racionNueva) {
        Racion racionExistente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ración no encontrada"));

        // Validar Fechas nuevamente
        validarFechas(racionNueva);
        
        // Actualizar datos 
        racionExistente.setStockPreparado(racionNueva.getStockPreparado());
        racionExistente.setFechaPreparacion(racionNueva.getFechaPreparacion());
        racionExistente.setFechaVencimiento(racionNueva.getFechaVencimiento());
        
        // Si cambia la receta asociada
        if(racionNueva.getReceta() != null && racionNueva.getReceta().getId() != null){
             Receta receta = recetaRepo.findById(racionNueva.getReceta().getId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));
             racionExistente.setReceta(receta);
        }

        /* No actualizamos Stock Restante aca, el requisito dice que no se edita por PUT.
         Solo se modifica mediante lógica de negocio de "entregas" (futuro endpoint).
        */
        return repo.save(racionExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Ración no encontrada");
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Racion buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ración no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Racion> listarTodas() {
        return repo.findAll();
    }

    private void validarFechas(Racion racion) {
        if (racion.getFechaVencimiento().isBefore(racion.getFechaPreparacion()) || 
            racion.getFechaVencimiento().isEqual(racion.getFechaPreparacion())) {
            throw new IllegalArgumentException("La fecha de vencimiento debe ser posterior a la fecha de preparación.");
        }
    }
}