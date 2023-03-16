package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Participante;

public interface FichaService{

    public FichaEleccion getFichaEleccion(Participante participante, Integer numEleccion);
    public boolean existe(Participante participante);
    public void crearFichas(Participante participante);
    public void updateFichaEleccion(FichaEleccion ficha);
    public FichaEleccion findEById(Integer id);

}