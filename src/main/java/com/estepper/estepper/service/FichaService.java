package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.FichaObjetivo;
import com.estepper.estepper.model.entity.FichaTaller;
import com.estepper.estepper.model.entity.Participante;

public interface FichaService{

    public FichaEleccion getFichaEleccion(Participante participante, Integer numEleccion);
    public FichaTaller getFichaTaller(Participante participante);
    public FichaObjetivo getFichaObjetivo(Participante participante);
    public boolean existe(Participante participante);
    public void crearFichas(Participante participante);
    public void updateFichaEleccion(FichaEleccion ficha);
    public void updateFichaTaller(FichaTaller ficha);
    public void updateFichaObjetivo(FichaObjetivo ficha);
    public FichaEleccion findEById(Integer id);
    public void deleteByParticipante(Participante p);

}