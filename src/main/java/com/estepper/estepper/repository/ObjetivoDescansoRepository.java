package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.ObjetivoDescanso;


import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Participante;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ObjetivoDescansoRepository extends JpaRepository<ObjetivoDescanso, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM ObjetivoDescanso o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);

    ObjetivoDescanso findByFechaAndParticipante(Date fecha, Participante p);
    ObjetivoDescanso findByParticipante(Participante p);
   // List<ObjetivoDescanso> findByFechaAndParticipante(Date fecha, Participante p);


}





