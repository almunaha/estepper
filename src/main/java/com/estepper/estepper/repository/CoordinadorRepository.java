package com.estepper.estepper.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Coordinador;


public interface CoordinadorRepository extends JpaRepository<Coordinador, Integer> {

    Optional<Coordinador> findById(Integer id);
 
}
