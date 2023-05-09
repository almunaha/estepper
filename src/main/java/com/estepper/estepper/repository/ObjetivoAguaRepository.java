package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.ObjetivoAgua;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Participante;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObjetivoAguaRepository extends JpaRepository<ObjetivoAgua, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ObjetivoAgua o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);

    ObjetivoAgua findByFechaAndParticipante(Date fecha, Participante p);

}
