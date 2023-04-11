package com.estepper.estepper.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Coordinador;

import com.estepper.estepper.repository.CoordinadorRepository;


@Service
public class CoordinadorServiceImpl implements CoordinadorService{

    @Autowired
    private CoordinadorRepository repo; //inyección de dependencias del participante dao api


    @Override
    public Coordinador getCoordinador(Integer id){
        return repo.findById(id).get();
    }   


    
}
