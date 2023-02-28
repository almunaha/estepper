package com.estepper.estepper.service;

import com.estepper.estepper.repository.ObjetivoRepository;
import com.estepper.estepper.model.entity.Objetivo;

import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.entity.Participante;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoServiceImpl implements ObjetivoService {
    
    @Autowired
    private ObjetivoRepository repo;

    @Override
    public void guardar(Objetivo obj){
        repo.save(obj);
    }

    /*@Override
    public List<Objetivo> objetivosPorEstado(Participante participante, EstadoObjetivo estado){
        return repo.findByParticipanteyEstadoObjetivo(participante, estado);
    }

    @Override
    public List<Objetivo> ObjetivosPorFecha(Date fecha, EstadoObjetivo estado, Participante participante){
        return repo.findByFechaAfteryEstadoObjetivoyParticipante(fecha, estado, participante);
    }*/

    @Override
    public List<Objetivo> listaObjetivos() {
        return(List<Objetivo>) repo.findAll();    
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public Objetivo getObjetivo(Integer id) {
        return repo.findById(id).get();    
    }


}

