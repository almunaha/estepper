package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.ObjetivoEjercicio;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Participante;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObjetivoEjercicioRepository extends JpaRepository<ObjetivoEjercicio, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ObjetivoEjercicio o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);

    ObjetivoEjercicio findByParticipante(Participante p);

    List<ObjetivoEjercicio> findByFechaAndParticipante(Date fecha, Participante p);

}
