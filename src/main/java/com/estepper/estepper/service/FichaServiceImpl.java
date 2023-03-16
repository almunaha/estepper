package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.FichaEleccionRepository;

@Service
public class FichaServiceImpl implements FichaService{

    @Autowired
    private FichaEleccionRepository repoE;

    @Override
    public FichaEleccion getFichaEleccion(Participante participante, Integer numEleccion) {
        return repoE.findByParticipanteAndNumEleccion(participante, numEleccion);
    }

    
}