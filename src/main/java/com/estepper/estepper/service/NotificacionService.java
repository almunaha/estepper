package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Notificacion;
import com.estepper.estepper.model.entity.Participante;

public interface NotificacionService {
    public void guardar(Notificacion n);

    public List<Notificacion> notificaciones(Participante p);

    public void eliminar(Notificacion n);

}
