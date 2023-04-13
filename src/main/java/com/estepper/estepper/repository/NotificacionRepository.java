package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Notificacion;

public interface NotificacionRepository  extends JpaRepository<Notificacion, Integer>{
    
}
