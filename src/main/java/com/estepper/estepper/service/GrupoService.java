package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface GrupoService {

    public List<Grupo> listaGrupos(Integer id);

    public Grupo getGrupo(Integer id);

    public Grupo findByCodigo(String codigo);

    public void updateParticipantes(Integer idGrupo, Integer numParticipantes);

    public void delete(Integer id);

    public void save(Grupo grupo);

    public Grupo findByNombre(String nombre);

    public void update(Grupo grupo);

    public List<Grupo> getGrupos();

    public Page<Grupo> paginas(Pageable pageable, Coordinador coordinador);

}
