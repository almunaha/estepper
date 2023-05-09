package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;

public interface SesionService {
    public Sesion buscarSesion(Participante participante, Integer numSesion);

    public void guardar(Sesion s);

    public List<Sesion> sesiones(Participante participante);

    public void deleteByParticipante(Participante p);

}
