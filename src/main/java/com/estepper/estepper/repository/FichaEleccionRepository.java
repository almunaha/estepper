package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface FichaEleccionRepository extends JpaRepository<FichaEleccion, Integer>{
    FichaEleccion findByParticipanteAndNumEleccion(Participante participante,Integer numEleccion);

    @Modifying
    @Transactional
    @Query("UPDATE FichaEleccion e SET e.texto = :texto  WHERE e.id = :id")
    void update(String texto, Integer id);
}
