package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.repository.GrupoRepository;


@Service
public class GrupoServiceImpl implements GrupoService{
    
    @Autowired
    private GrupoRepository repo; //inyección de dependencias del grupo dao api

    @Override
    public List<Grupo> listaGrupos() {
        return(List<Grupo>) repo.findAll();
    }  

    @Override
    public Grupo getGrupo(Integer id){
        return repo.findById(id).get();
    }    

    
    @Override
    public void updateParticipantes(Integer idGrupo, Integer numParticipantes){
        repo.update(idGrupo, numParticipantes);
    }    


}






