package com.tpi.daos.controller;


	import com.tpi.daos.entity.Receta;
	import com.tpi.daos.service.RecetaService;
	import org.springframework.hateoas.CollectionModel;
	import org.springframework.hateoas.EntityModel;
	import org.springframework.web.bind.annotation.*;

	import java.util.List;

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

	        // Más adelante acá agregamos el link HATEOAS a las raciones de esta receta (S02)

	        return model;
	    }

	    // GET /recetas
	    @GetMapping
	    public CollectionModel<EntityModel<Receta>> listar() {
	        List<EntityModel<Receta>> recetas = service.listarTodas().stream()
	                .map(r -> EntityModel.of(r,
	                        linkTo(methodOn(RecetaController.class).getById(r.getId())).withSelfRel()))
	                .toList();

	        return CollectionModel.of(recetas,
	                linkTo(methodOn(RecetaController.class).listar()).withSelfRel());
	    }

	    // POST /recetas
	    @PostMapping
	    public EntityModel<Receta> crear(@RequestBody Receta receta) {
	        Receta creada = service.crear(receta);
	        return EntityModel.of(creada,
	                linkTo(methodOn(RecetaController.class).getById(creada.getId())).withSelfRel());
	    }

	    // PUT /recetas/{id}
	    @PutMapping("/{id}")
	    public EntityModel<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
	        Receta actualizada = service.actualizar(id, receta);
	        return EntityModel.of(actualizada,
	                linkTo(methodOn(RecetaController.class).getById(actualizada.getId())).withSelfRel());
	    }

	    // DELETE /recetas/{id}
	    @DeleteMapping("/{id}")
	    public void eliminar(@PathVariable Long id) {
	        service.eliminar(id);
	    }
	}


