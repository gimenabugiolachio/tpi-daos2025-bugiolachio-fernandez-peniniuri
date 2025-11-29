package com.tpi.daos.controller;

import com.tpi.daos.entity.Asistencia;
import com.tpi.daos.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return ResponseEntity.ok(asistencia);
    }

}
