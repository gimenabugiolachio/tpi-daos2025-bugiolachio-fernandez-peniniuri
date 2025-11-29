package com.tpi.daos.service;

import com.tpi.daos.entity.Ciudad;
import com.tpi.daos.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService{
    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public Optional<Ciudad> findCiudadById(Long ciudadId) {
        return ciudadRepository.findById(ciudadId);
    }
}
