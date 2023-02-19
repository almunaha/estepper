package com.estepper.estepper.service;

import java.util.Optional;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;

public interface SesionService {
    public Sesion buscarSesion(Participante participante, Integer numSesion);
    public void guardar(Sesion s);
    
}
