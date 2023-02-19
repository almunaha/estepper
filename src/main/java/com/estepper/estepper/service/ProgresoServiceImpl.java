package com.estepper.estepper.service;
import com.estepper.estepper.repository.ProgresoRepository;
import com.estepper.estepper.model.entity.Progreso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgresoServiceImpl implements ProgresoService{

    @Autowired
    private ProgresoRepository repo;

    public void guardar(Progreso s){
        repo.save(s);
    }
    
}
