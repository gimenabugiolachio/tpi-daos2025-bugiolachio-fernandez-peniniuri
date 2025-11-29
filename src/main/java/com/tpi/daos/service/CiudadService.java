package com.tpi.daos.service;

import com.tpi.daos.entity.Ciudad;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    Optional<Ciudad> findCiudadById(Long ciudadId);
}
