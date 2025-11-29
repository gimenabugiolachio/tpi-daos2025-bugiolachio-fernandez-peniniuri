package com.tpi.daos.controller;

import com.tpi.daos.entity.Ciudad;
import com.tpi.daos.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCiudadById(@PathVariable Long id){
        Optional<Ciudad> ciudad = ciudadService.findCiudadById(id);
        if(ciudad.isEmpty()){
            return new ResponseEntity<>("No se encontró ciudad con id " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ciudad);
    }
}
