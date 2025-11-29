package com.tpi.daos.controller;

import com.tpi.daos.dto.AsistidoDTO;
import com.tpi.daos.entity.Asistido;
import com.tpi.daos.entity.Ciudad;
import com.tpi.daos.service.AsistidoService;
import com.tpi.daos.service.CiudadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/asistidos")
public class AsistidoController {
    @Autowired
    private AsistidoService asistidoService;

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public ResponseEntity<?> getAsistidos() {
        List<Asistido> asistidoList = asistidoService.getAsistidos();

        if (asistidoList.isEmpty()) {
            return new ResponseEntity<>("No se encuentran cargados asistidos", HttpStatus.OK);
        }

        List<EntityModel<Asistido>> asistidosConLinks = asistidoList.stream()
                .map(asistido -> EntityModel.of(
                        asistido,
                        linkTo(methodOn(AsistidoController.class)
                                .getAsistido(asistido.getId())).withSelfRel(),

                        linkTo(methodOn(AsistenciaController.class)
                                .findAsistenciaById(asistido.getId())).withRel("asistencias"),

                        linkTo(methodOn(CiudadController.class)
                                .getCiudadById(asistido.getCiudad().getId()))
                                .withRel("ciudad")
                ))
                .toList();

        CollectionModel<EntityModel<Asistido>> model = CollectionModel.of(
                asistidosConLinks,
                linkTo(methodOn(AsistidoController.class).getAsistidos()).withSelfRel()
        );

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAsistido(@PathVariable Long id) {
        Optional<Asistido> asistido = asistidoService.getAsistidoById(id);
        if (asistido.isEmpty()) {
            return new ResponseEntity<>("No existe persona asistida con el id " + id, HttpStatus.NOT_FOUND);
        }
        Asistido asistidoHateoas = asistido.get();

        EntityModel<Asistido> model = EntityModel.of(
                asistidoHateoas,
                linkTo(methodOn(AsistidoController.class).getAsistido(id)).withSelfRel(),
                linkTo(methodOn(AsistenciaController.class).findAsistenciaById(id)).withRel("asistencias"),
                linkTo(methodOn(CiudadController.class).getCiudadById(id)).withRel("ciudad")

        );

        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<?> createAsistido(@Valid @RequestBody AsistidoDTO asistido) {
        if (asistidoService.findByNombre(asistido.getNombre()).isPresent()) {
            return new ResponseEntity<>("Ya existe una persona registrada bajo el nombre " + (asistido.getNombre()), HttpStatus.CONFLICT);
        }

        if (asistidoService.findByDni(asistido.getDni()).isPresent()) {
            return new ResponseEntity<>("Ya existe una persona registrada con el DNI " + (asistido.getDni()), HttpStatus.CONFLICT);
        }

        Optional<Ciudad> ciudad = ciudadService.findCiudadById(asistido.getIdCiudad());
        if (ciudad.isEmpty()) {
            return new ResponseEntity<>("La ciudad con id " + asistido.getIdCiudad() + " no se encuentra cargado", HttpStatus.NOT_FOUND);
        } else {
            asistido.setCiudad(ciudad.orElseThrow());
            asistidoService.createAsistido(asistido);
            return new ResponseEntity<Asistido>(HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateAsistido(@Valid @RequestBody AsistidoDTO asistido) {
        if (asistidoService.findByNombre(asistido.getNombre()).isEmpty()) {
            return new ResponseEntity<>("No existe una persona registrada bajo el nombre " + (asistido.getNombre()), HttpStatus.CONFLICT);
        }

        Optional<Ciudad> ciudad = ciudadService.findCiudadById(asistido.getIdCiudad());
        if (ciudad.isEmpty()) {
            return new ResponseEntity<>("La ciudad con id " + asistido.getIdCiudad() + " no se encuentra cargado", HttpStatus.NOT_FOUND);
        } else {
            asistido.setCiudad(ciudad.orElseThrow());
            asistidoService.updateAsistido(asistido);
            return new ResponseEntity<Asistido>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsistido(@PathVariable Long id) {
        if (asistidoService.getAsistidoById(id).isEmpty()) {
            return new ResponseEntity<>("No existe persona asistida con el id " + id, HttpStatus.NOT_FOUND);
        } else {
            asistidoService.deleteAsistido(id);
            return new ResponseEntity<Asistido>(HttpStatus.NO_CONTENT);
        }
    }
}
