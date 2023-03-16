package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Participante;

public interface FichaService{

    public FichaEleccion getFichaEleccion(Participante participante, Integer numEleccion);

}