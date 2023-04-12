package com.estepper.estepper.service;

import java.util.Date;
import java.util.List;

import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;

public interface ObjetivoService {

    //OBJETIVOS PERSONALIZADOS
    public List<Objetivo> listaObjetivos(Participante p);
    public List<Objetivo> listaObjetivosPorMes(Participante p, Integer mes, Integer anio);
    public void guardar(Objetivo o);
    public void borrar(Integer id);
    public Objetivo getObjetivo(Integer id);
    public List<Objetivo> getObjetivos(); 
    public void deleteByParticipante(Participante p);

   //OBJETIVOS RECOMENDADOS
   
   //AGUA
   public void guardarAgua(ObjetivoAgua o);
   public void borrarObjetivoAgua(Integer id);
   public ObjetivoAgua getObjetivoAgua(Integer id);
   public ObjetivoAgua findByFechaAndParticipanteAgua(Date fecha,Participante p);
   //DESCANSO
   public void guardarDescanso(ObjetivoDescanso o);
   public void borrarObjetivoDescanso(Integer id);
   public ObjetivoDescanso getObjetivoDescanso(Integer id);
   public ObjetivoDescanso findByFechaAndParticipanteDescanso(Date fecha,Participante p);
   public ObjetivoDescanso findByParticipanteDescanso(Participante p);
   //EJERCICIO
   public void guardarEjercicio(ObjetivoEjercicio o);
   public void borrarObjetivoEjercicio(Integer id);
   public ObjetivoEjercicio getObjetivoEjercicio(Integer id);
   public ObjetivoEjercicio findByParticipanteEjercicio(Participante p);
   public List<ObjetivoEjercicio> listaEjercicio(Date fecha, Participante p);
   //ESTADO ANIMO
   public void guardarEstadoAnimo(ObjetivoEstadoAnimo o);
   public void borrarObjetivoEstadoAnimo(Integer id);
   public ObjetivoEstadoAnimo getObjetivoEstadoAnimo(Integer id);
   public ObjetivoEstadoAnimo findByFechaAndParticipanteEstadoAnimo(Date fecha,Participante p);
   public ObjetivoEstadoAnimo findByParticipanteEstadoAnimo(Participante p);

}

