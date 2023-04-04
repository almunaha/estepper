package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.entity.Participante;

public interface ObjetivoService {

    public List<Objetivo> listaObjetivos(Participante p);
    public void guardar(Objetivo o);
    public void borrar(Integer id);
    public Objetivo getObjetivo(Integer id);
    public List<Objetivo> getObjetivos(); 
   // public List<Objetivo> objetivosPorEstado(Participante participante, EstadoObjetivo estado);
   // public List<Objetivo> ObjetivosPorFecha(Date fecha, EstadoObjetivo estado, Participante participante);
   public void deleteByParticipante(Participante p);
}

