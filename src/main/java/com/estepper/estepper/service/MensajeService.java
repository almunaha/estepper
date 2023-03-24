package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.Usuario;

public interface MensajeService {
 
    public Mensaje getMensaje(Integer id);
    public void save(Mensaje mensaje);
    public List<Mensaje> obtenerMensajes();
    public void deleteByParticipante(Usuario p);
    public void deleteByGrupo(Grupo g);
}
