package com.estepper.estepper.service;


import java.util.Date;
import java.util.List;

import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;


public interface ObjetivoEstadoAnimoService {

    public void guardar(ObjetivoEstadoAnimo o);
    public void borrar(Integer id);
    public ObjetivoEstadoAnimo getObjetivoEstadoAnimo(Integer id);
    public ObjetivoEstadoAnimo findByFechaAndParticipante(Date fecha,Participante p);
    public ObjetivoEstadoAnimo findByParticipante(Participante p);
    //public List<ObjetivoEstadoAnimo> listaEstadoAnimo(Date fecha, Participante p);



}

