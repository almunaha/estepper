package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.FichaTaller;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.repository.FichaEleccionRepository;
import com.estepper.estepper.repository.FichaTallerRepository;
import com.estepper.estepper.repository.FichaRepository;

@Service
public class FichaServiceImpl implements FichaService{

    @Autowired
    private FichaEleccionRepository repoE;

    @Autowired 
    private FichaRepository repo;

    @Autowired 
    private FichaTallerRepository repoT;

    @Override
    public FichaEleccion getFichaEleccion(Participante participante, Integer numEleccion) {
        return repoE.findByParticipanteAndNumEleccion(participante, numEleccion);
    }

    @Override
    public boolean existe(Participante participante) {
        return repo.existsByParticipante(participante);
    }

    @Override
    public void crearFichas(Participante participante) {
        //CREAR FICHAS ELECCION
        FichaEleccion ficha = new FichaEleccion(0, participante, "", 1);
        repoE.save(ficha);
        ficha = new FichaEleccion(0, participante, "", 2);
        repoE.save(ficha);
        ficha = new FichaEleccion(0, participante, "", 3);
        repoE.save(ficha);
        ficha = new FichaEleccion(0, participante, "", 4);
        repoE.save(ficha);

        //CREAR FICHAS SALUD
        FichaTaller fichaT = new FichaTaller(0, participante, "", "", "", 0, 0);
        repoT.save(fichaT);

        //CREAR FICHAS PESO SALUDABLE
    }

    @Override
    public void updateFichaEleccion(FichaEleccion ficha) {
        repoE.update(ficha.getTexto(), ficha.getId());
    }

    @Override
    public FichaEleccion findEById(Integer id) {
        return repoE.findById(id).get();
    }

    
}