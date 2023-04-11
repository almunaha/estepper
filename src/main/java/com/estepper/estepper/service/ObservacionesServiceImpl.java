package com.estepper.estepper.service;

import com.estepper.estepper.repository.ObservacionesRepository;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Observaciones;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservacionesServiceImpl implements ObservacionesService {
    
    @Autowired
    private ObservacionesRepository repo;

    @Override
    public List<Observaciones> findByIdCoordinador(Integer idCoordinador) {
        return(List<Observaciones>) repo.findByIdCoordinador(idCoordinador);
    }  

    @Override
    public List<Observaciones> findByIdGrupo(Integer idGrupo) {
        return(List<Observaciones>) repo.findByIdGrupo(idGrupo);
    }  


    @Override
    public Observaciones getObservacion(Integer idObservacion){
        return repo.findById(idObservacion).get();
    }


    /* 
     * 
     * 
    @Override
    public List<Observaciones> listaObservacionesGrupoCoordinador(Integer idCoordinador, Integer idGrupo) {
        return(List<Observaciones>) repo.findByIdCoordinadoryIdGrupo(idCoordinador,idGrupo);    
    }
    */


    @Override
    public void guardar(Observaciones o){
        repo.save(o);
    }


    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }


    @Override
    public void deleteByGrupo(Grupo g) {
        repo.deleteAllByGrupo(g);
    }


    @Override
    public void actualizar(Observaciones o){
        repo.update(o.getNota(),o.getId());
    }




}
