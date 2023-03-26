package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Actividad;

import com.estepper.estepper.repository.ActividadRepository;

@Service
public class ActividadServiceImpl implements ActividadService{

    @Autowired
    private ActividadRepository repo;

    @Override
    public void guardar(Actividad a){
        repo.save(a); 
    }

    @Override
    public List<Actividad> listado() {
        return(List<Actividad>) repo.findAll();
    }

    @Override
    public Actividad actividad(Integer id){
        return repo.findById(id).get();
    }

    @Override
    public List<Actividad> asistenciaParticipante(Integer id){
        return(List<Actividad>) repo.findByParticipantesIdOrderByFechaRealizacionAsc(id);
    }
    
}
