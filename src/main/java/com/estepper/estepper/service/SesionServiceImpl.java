package com.estepper.estepper.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.repository.SesionRepository;

@Service
public class SesionServiceImpl implements SesionService{

    @Autowired
    private SesionRepository repo;  //inyección de dependencia

    public void guardar(Sesion s){
        repo.save(s); 
    }

    public Sesion buscarSesion(Participante participante, Integer numSesion){ 
        return repo.findByParticipanteAndNumSesion(participante, numSesion);
    }

    public List<Sesion> sesiones(Participante participante){
        return repo.findByParticipante(participante);
    }
    
}
