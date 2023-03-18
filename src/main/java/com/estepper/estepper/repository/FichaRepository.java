package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface FichaRepository extends JpaRepository<Ficha, Integer>{
    boolean existsByParticipante(Participante participante);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ficha f WHERE f.participante = :p")
    void deleteAllByParticipante(Participante p);
}
