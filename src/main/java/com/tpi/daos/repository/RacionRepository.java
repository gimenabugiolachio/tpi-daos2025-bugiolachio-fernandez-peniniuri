package com.tpi.daos.repository;

import com.tpi.daos.entity.Racion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacionRepository extends JpaRepository<Racion, Long> {
}