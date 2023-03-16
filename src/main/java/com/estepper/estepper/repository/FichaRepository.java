package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Participante;

public interface FichaRepository extends JpaRepository<Ficha, Integer>{
    boolean existsByParticipante(Participante participante);
}
