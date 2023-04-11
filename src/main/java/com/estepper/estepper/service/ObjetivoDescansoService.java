package com.estepper.estepper.service;


import java.util.Date;

import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.Participante;


public interface ObjetivoDescansoService {

    public void guardar(ObjetivoDescanso o);
    public void borrar(Integer id);
    public ObjetivoDescanso getObjetivoDescanso(Integer id);
    public ObjetivoDescanso findByFechaAndParticipante(Date fecha,Participante p);
    public ObjetivoDescanso findByParticipante(Participante p);
    //public List<ObjetivoDescanso> listaDescanso(Date fecha, Participante p);



}

