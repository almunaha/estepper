package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Actividad;

import java.time.LocalDateTime;
import java.util.List;


public interface ActividadRepository extends JpaRepository<Actividad, Integer>{ 
    List<Actividad> findByParticipantesIdOrderByFechaRealizacionAsc(Integer participanteId);

    @Query(value = "SELECT COUNT(*) > 0 FROM asistencia_actividades WHERE id_actividad = :actividadId AND id_participante = :participanteId", nativeQuery = true)
    Integer asistencia(Integer actividadId, Integer participanteId);

    @Query("SELECT a FROM Actividad a WHERE a.fechaRealizacion > :fechaActual")
    List<Actividad> findActividadesPendientes(LocalDateTime fechaActual);

}
