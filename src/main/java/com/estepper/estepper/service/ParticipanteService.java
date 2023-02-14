package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;
import com.estepper.estepper.model.enums.Sexo;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

public interface ParticipanteService {
    public List<Participante> listado();
    public Optional<Participante> findById(Integer id);   
    public Participante getParticipante(Integer id); //prueba
    public void update(Integer idParticipante, Grupo grupo);
    public List<Participante> listadoGrupo(Grupo grupo);
    public void update1(Sexo sexo, Integer id);
}
