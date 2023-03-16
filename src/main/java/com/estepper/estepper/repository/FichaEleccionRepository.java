package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Participante;

public interface FichaEleccionRepository extends JpaRepository<FichaEleccion, Integer>{
    FichaEleccion findByParticipanteAndNumEleccion(Participante participante,Integer numEleccion);
    
}
