package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.Participante;

public interface MensajePrivadoService {
 
    public MensajePrivado getMensaje(Integer id);
    public void save(MensajePrivado mensaje);
    public List<MensajePrivado> obtenerMensajesPrivados(Participante participante);
    public void deleteByParticipante(Participante p);
    public void deleteByCoordinador(Coordinador c);
}
