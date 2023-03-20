package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Integer>{
    
}
