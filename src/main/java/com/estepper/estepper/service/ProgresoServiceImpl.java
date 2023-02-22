package com.estepper.estepper.service;
import com.estepper.estepper.repository.ProgresoRepository;
import com.estepper.estepper.model.entity.Progreso;

import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgresoServiceImpl implements ProgresoService{

    @Autowired
    private ProgresoRepository repo;

    @Override
    public void guardar(Progreso s){
        repo.save(s);
    }

    @Override
    public List<Progreso> datos(Participante participante, TipoProgreso tipo){
        return repo.findByParticipanteAndTipo(participante, tipo);
    }

    
}
