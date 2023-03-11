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

    @Override
    public List<Objetivo> getObjetivos(){
        return repo.findAll();
    }


    @Override
    public List<Objetivo> listaObjetivos(Participante p) {
        return(List<Objetivo>) repo.findByParticipante(p);    
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public Objetivo getObjetivo(Integer id) {
        return repo.findById(id).get();    
    }

    @Override
    public void deleteByParticipante(Participante p) {
        repo.deleteAllByParticipante(p);
    }


}

