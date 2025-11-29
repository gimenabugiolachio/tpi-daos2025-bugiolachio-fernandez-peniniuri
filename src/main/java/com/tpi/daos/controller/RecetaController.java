package com.tpi.daos.controller;

import com.tpi.daos.entity.Receta;
import com.tpi.daos.service.RecetaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Importamos estáticamente los constructores de links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService service;

    public RecetaController(RecetaService service) {
        this.service = service;
    }

    // GET /recetas/{id}
    @GetMapping("/{id}")
    public EntityModel<Receta> getById(@PathVariable Long id) {
        Receta receta = service.buscarPorId(id);

        EntityModel<Receta> model = EntityModel.of(receta);

        model.add(linkTo(methodOn(RecetaController.class).getById(id)).withSelfRel());

        model.add(linkTo(methodOn(RecetaController.class).listar()).withRel("todas-las-recetas"));


        return model;
        return toModel(receta);
    }

    // GET /recetas
    @GetMapping
    public CollectionModel<EntityModel<Receta>> listar() {
        List<EntityModel<Receta>> recetas = service.listarTodas().stream()
                .map(r -> EntityModel.of(
                        r,
                        linkTo(methodOn(RecetaController.class).getById(r.getId())).withSelfRel()
                ))
                .toList();

        return CollectionModel.of(
                recetas,
                linkTo(methodOn(RecetaController.class).listar()).withSelfRel()
        );
                .map(this::toModel) // Usamos el método auxiliar para consistencia
                .toList();

        return CollectionModel.of(recetas,
                linkTo(methodOn(RecetaController.class).listar()).withSelfRel());
    }

    // POST /recetas
    @PostMapping
    public EntityModel<Receta> crear(@RequestBody Receta receta) {
        Receta creada = service.crear(receta);

        return EntityModel.of(
                creada,
                linkTo(methodOn(RecetaController.class).getById(creada.getId())).withSelfRel(),
                linkTo(methodOn(RecetaController.class).listar()).withRel("todas-las-recetas")
        );
        return toModel(creada);
    }

    // PUT /recetas/{id}
    @PutMapping("/{id}")
    public EntityModel<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        Receta actualizada = service.actualizar(id, receta);

        return EntityModel.of(
                actualizada,
                linkTo(methodOn(RecetaController.class).getById(actualizada.getId())).withSelfRel(),
                linkTo(methodOn(RecetaController.class).listar()).withRel("todas-las-recetas")
        );
        return toModel(actualizada);
    }

    // DELETE /recetas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

    // Método auxiliar para armar la respuesta HATEOAS
    private EntityModel<Receta> toModel(Receta receta) {
        return EntityModel.of(receta,
                linkTo(methodOn(RecetaController.class).getById(receta.getId())).withSelfRel(),
                // Requisito S04: Link a las preparaciones (Raciones)
                // Apuntamos al listar de RacionController con la relación "preparaciones"
                linkTo(methodOn(RacionController.class).listar()).withRel("preparaciones")
        );
    }
}
