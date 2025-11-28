package com.tpi.daos.controller;

import com.tpi.daos.entity.Racion;
import com.tpi.daos.service.RacionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/raciones")
public class RacionController {

    private final RacionService service;

    public RacionController(RacionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EntityModel<Racion> getById(@PathVariable Long id) {
        Racion racion = service.buscarPorId(id);
        return toModel(racion);
    }

    @GetMapping
    public CollectionModel<EntityModel<Racion>> listar() {
        List<EntityModel<Racion>> raciones = service.listarTodas().stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(raciones,
                linkTo(methodOn(RacionController.class).listar()).withSelfRel());
    }

    @PostMapping
    public EntityModel<Racion> crear(@RequestBody Racion racion) {
        Racion creada = service.crear(racion);
        return toModel(creada);
    }

    @PutMapping("/{id}")
    public EntityModel<Racion> actualizar(@PathVariable Long id, @RequestBody Racion racion) {
        Racion actualizada = service.actualizar(id, racion);
        return toModel(actualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // Para construir el HATEOAS y evitar repetir código
    private EntityModel<Racion> toModel(Racion racion) {
        return EntityModel.of(racion,
                linkTo(methodOn(RacionController.class).getById(racion.getId())).withSelfRel(),
                // Link requerido por la consigna: ir a ver la receta de esta ración
                linkTo(methodOn(RecetaController.class).getById(racion.getReceta().getId())).withRel("receta")
        );
    }
}