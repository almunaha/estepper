package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.GrupoRepository;
import com.estepper.estepper.repository.ParticipanteRepository;


@Service
public class GrupoServiceImpl implements GrupoService{
    
    @Autowired
    private GrupoRepository repo; //inyección de dependencias del grupo dao api

    @Autowired ParticipanteRepository repoP;

    @Override
    public List<Grupo> listaGrupos(Integer id) {
        return(List<Grupo>) repo.findByIdCoordinador(id);
    }  

    @Override 
    public void update (Grupo grupo){
        repo.update(grupo.getNombre(), grupo.getCodigo(), grupo.getIdCoordinador(), grupo.getNumParticipantes(), grupo.getId());
    }

    @Override
    public Grupo findByCodigo(String codigo){
        return repo.findByCodigo(codigo);
    }

    @Override
    public Grupo getGrupo(Integer id){
        return repo.findById(id).get();
    }    

    @Override
    public void delete(Integer id) {
        Grupo grupo = repo.findById(id).get();
        List<Participante> p = repoP.findByGrupo(grupo);
        for(int i = 0; i < p.size(); i++){
            p.get(i).setGrupo(null);
        }
        repo.delete(grupo);
    }   
    
    @Override
    public void save(Grupo grupo) {
        repo.save(grupo);
    }   



}






