package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.AlimentacionRepository;
import com.estepper.estepper.repository.AlimentosConsumidosRepository;

@Service
public class AlimentacionServiceImpl implements AlimentacionService{

    @Autowired
    private AlimentacionRepository repoA;

    @Autowired
    private AlimentosConsumidosRepository repoAC;

    @Override
    public void deleteByParticipante(Participante p) {
        repoAC.deleteAllByParticipante(p);
    }

    
}
