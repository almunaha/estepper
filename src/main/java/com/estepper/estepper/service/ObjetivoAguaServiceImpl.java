package com.estepper.estepper.service;

import com.estepper.estepper.repository.ObjetivoAguaRepository;

import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.Participante;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoAguaServiceImpl implements ObjetivoAguaService {
    
    @Autowired
    private ObjetivoAguaRepository repo;

    @Override
    public void guardar(ObjetivoAgua obj){
        repo.save(obj);
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public ObjetivoAgua getObjetivoAgua(Integer id) {
        return repo.findById(id).get();    
    }

    @Override
    public ObjetivoAgua findByFechaAndParticipante(Date fecha,Participante p){
        return repo.findByFechaAndParticipante(fecha,p);
    }



}

