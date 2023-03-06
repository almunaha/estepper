package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.enums.EstadoObjetivo;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Participante;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer>{

    List<Objetivo> findByParticipante(Participante participante);
    ///List<Objetivo> findByFechaAfteryEstadoObjetivoyParticipante(Date fecha, EstadoObjetivo estado, Participante participante);
    //Objetivo findByIdObjetivo(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Objetivo o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);
}





