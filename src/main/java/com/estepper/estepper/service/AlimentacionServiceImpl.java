package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.AlimentosConsumidos;
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

    @Override
    public List<Alimentacion> getAlimentos() {
        return repoA.findAll();
    }

    @Override
    public List<AlimentosConsumidos> getAlimentosCon(Participante p) {
        return repoAC.findByParticipante(p);
    }

    @Override
    public void saveAlCon(AlimentosConsumidos al) {
        repoAC.save(al);
    }

    @Override
    public void saveAlimento(Alimentacion al) {
        repoA.save(al);
    }

    
}
