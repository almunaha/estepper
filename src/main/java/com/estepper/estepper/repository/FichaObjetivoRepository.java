package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.FichaObjetivo;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface FichaObjetivoRepository extends JpaRepository<FichaObjetivo, Integer> {
    FichaObjetivo findByParticipante(Participante participante);

    @Modifying
    @Transactional
    @Query("UPDATE FichaObjetivo o SET o.objetivo = :objetivo, o.perdida = :perdida WHERE o.id = :id")
    void update(Double objetivo, Double perdida, Integer id);

}
