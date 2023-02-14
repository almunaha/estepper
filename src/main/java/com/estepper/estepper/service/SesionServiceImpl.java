package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.repository.SesionRepository;

//crear métodos y lógica
@Service
public class SesionServiceImpl implements SesionService{

    @Autowired
    private SesionRepository repo;  //inyección de dependencia

    public void guardar(Sesion s){
        repo.save(s); 
    }

    public Sesion buscarSesion(Integer id, Integer numSesion){ //hacerlo en una sola consulta mejor
        return repo.findByIdParticipante(id);
    }
    
}
