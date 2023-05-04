package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.FichaTaller;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface FichaTallerRepository extends JpaRepository<FichaTaller, Integer> {

    FichaTaller findByParticipante(Participante participante);

    @Modifying
    @Transactional
    @Query("UPDATE FichaTaller t SET t.dificultades = :dificultades, t.capacidad = :capacidad, t.importancia = :importancia, t.razones = :razones, t.temores = :temores WHERE t.id = :id")
    void update(String dificultades, Integer capacidad, Integer importancia, String razones, String temores,
            Integer id);

}
