package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.repository.ParticipanteRepository;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{

    @Autowired
    private ParticipanteRepository repo; //inyección de dependencias del participante dao api

    @Override
    public List<Participante> listado(){ 
        return(List<Participante>) repo.findAll();
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return repo.findById(id);
    }
    @Override
    public Participante getParticipante(Integer id){
        return repo.findById(id).get();
    }   

    @Override
    public void update(Integer idParticipante, Integer edad, Grupo grupo){
        repo.update(idParticipante, edad, grupo);
    }   

    public List<Participante> listadoGrupo (Grupo grupo){
        return repo.findByGrupo(grupo);
    }
    
}
