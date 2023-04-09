package com.estepper.estepper.service;


import java.util.Date;
import java.util.List;

import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.Participante;


public interface ObjetivoEjercicioService {

    public void guardar(ObjetivoEjercicio o);
    public void borrar(Integer id);
    public ObjetivoEjercicio getObjetivoEjercicio(Integer id);
   // public ObjetivoEjercicio findByFechaAndParticipante(Date fecha,Participante p);
    public ObjetivoEjercicio findByParticipante(Participante p);
    public List<ObjetivoEjercicio> listaEjercicio(Date fecha, Participante p);



}

