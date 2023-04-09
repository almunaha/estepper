package com.estepper.estepper.service;


import java.util.Date;

import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.Participante;


public interface ObjetivoAguaService {

    public void guardar(ObjetivoAgua o);
    public void borrar(Integer id);
    public ObjetivoAgua getObjetivoAgua(Integer id);
    public ObjetivoAgua findByFechaAndParticipante(Date fecha,Participante p);


}

