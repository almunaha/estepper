package com.estepper.estepper.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.EstadoInvitacion;

import jakarta.transaction.Transactional;

public interface InvitacionRepository extends JpaRepository<Invitacion, Integer>{

    List<Invitacion> findByCoordinador(Coordinador c);
    List<Invitacion> findByCoordinadorAndActividad(Coordinador c, Actividad actividad);
    List<Invitacion> findByActividad(Actividad a);
    List<Invitacion> findByParticipanteAndEstado(Participante p, EstadoInvitacion e);
    Invitacion findByParticipanteAndActividad(Participante p, Actividad a);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Invitacion i WHERE i.participante = :participante")
    void deleteByParticipante(Participante participante);

    @Modifying
    @Transactional
    @Query("DELETE FROM Invitacion i WHERE i.coordinador = :coordinador")
    void deleteByCoordinador(Coordinador coordinador);


}

