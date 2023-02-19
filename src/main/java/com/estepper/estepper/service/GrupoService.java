package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Grupo;


public interface GrupoService {

    public List<Grupo> listaGrupos();
    public Grupo getGrupo(Integer id); 
    public void updateParticipantes(Integer idGrupo, Integer numParticipantes);
    public void delete(Integer id);
    public void save(Grupo grupo);

}




