package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Actividad;
import java.util.List;


public interface ActividadRepository extends JpaRepository<Actividad, Integer>{ 
    List<Actividad> findByParticipantesIdOrderByFechaRealizacionAsc(Integer participanteId);

}
