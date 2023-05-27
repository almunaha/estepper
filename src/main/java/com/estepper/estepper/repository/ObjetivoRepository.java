package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.Objetivo;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Participante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

    List<Objetivo> findByParticipante(Participante participante);

    @Modifying
    @Transactional
    @Query("DELETE FROM Objetivo o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);
    
    @Query("SELECT o FROM Objetivo o WHERE ((o.repeticion != 'ANUALMENTE' AND MONTH(o.fechaInicio) <= :mes AND MONTH(o.fechaVencimiento) >= :mes AND YEAR(o.fechaInicio) <= :anio AND YEAR(o.fechaVencimiento) >= :anio) OR (o.repeticion = 'ANUALMENTE' AND ((YEAR(o.fechaInicio) = :anio AND MONTH(o.fechaInicio) <= :mes) OR (YEAR(o.fechaVencimiento) = :anio AND MONTH(o.fechaInicio) >= :mes)))) AND o.participante = :p")
    List<Objetivo> findByParticipanteyMesyAnio(Participante p, Integer mes, Integer anio);

}
