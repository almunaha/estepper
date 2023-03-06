package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer>{
    
}
