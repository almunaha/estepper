package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Grupo;


public interface GrupoService {

    public List<Grupo> listaGrupos();
    public Grupo getGrupo(Integer id); //VER SI SERÍA ASÍ
    public void updateParticipantes(Integer idGrupo, Integer numParticipantes);

}




