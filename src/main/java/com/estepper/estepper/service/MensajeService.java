package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Mensaje;

public interface MensajeService {
 
    public Mensaje getMensaje(Integer id);
    public void save(Mensaje mensaje);
    public List<Mensaje> obtenerMensajes();

}
