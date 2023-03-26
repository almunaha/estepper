package com.estepper.estepper.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.EstadoInvitacion;

public interface InvitacionRepository extends JpaRepository<Invitacion, Integer>{

    List<Invitacion> findByCoordinador(Coordinador c);
    List<Invitacion> findByActividad(Actividad a);
    List<Invitacion> findByParticipanteAndEstado(Participante p, EstadoInvitacion e);
    
}
