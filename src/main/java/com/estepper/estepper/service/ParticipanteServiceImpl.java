package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;
import com.estepper.estepper.model.enums.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.ParticipanteRepository;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{

    @Autowired
    private ParticipanteRepository repo; //inyección de dependencias del participante dao api

    @Override
    public List<Participante> listado(){ //¿puedo hacer esto directamten sin pasar por usuario??
        return(List<Participante>) repo.findAll();
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void update(Sexo sexo, Integer id) {
        repo.update(sexo, id);
        
    }
    
}
