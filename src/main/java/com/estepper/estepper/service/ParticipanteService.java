package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.enums.Estado;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Participante;

public interface ParticipanteService {
    public List<Participante> listado(Integer idCoordinador, Estado estadoCuenta);
    public Optional<Participante> findById(Integer id);  
    public Participante getParticipante(Integer id); 
    public List<Participante> listadoGrupo(Grupo grupo);
    public void update(Integer edad, Sexo sexo, String fotoParticipante, Grupo grupo, Integer asistencia, Integer idCoor, Double perdidadepeso, Integer sesionescompletas, Integer id);
    public Page<Participante> paginas(Pageable pageable, Integer idCoordinador, Estado estadoCuenta);
    public void quitargrupo(Integer id);
}
