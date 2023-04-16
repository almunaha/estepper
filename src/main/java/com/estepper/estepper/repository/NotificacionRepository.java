package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Notificacion;
import com.estepper.estepper.model.entity.Participante;

public interface NotificacionRepository  extends JpaRepository<Notificacion, Integer>{

    List<Notificacion> findByParticipanteOrderByFechaEnvioDesc(Participante participante);

    
}
