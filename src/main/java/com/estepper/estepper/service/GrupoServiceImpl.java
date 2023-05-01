package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.repository.CoordinadorRepository;
import com.estepper.estepper.repository.GrupoRepository;
import com.estepper.estepper.repository.ParticipanteRepository;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository repo; // inyección de dependencias del grupo dao api

    @Autowired
    ParticipanteRepository repoP;

    @Autowired
    CoordinadorRepository repoC;

    /*@Override
    public List<Grupo> listaGrupos(Integer id) {
        return (List<Grupo>) repo.findByIdCoordinador(id);
    }*/

    @Override
    public List<Grupo> listaGrupos(Integer id) {
        return (List<Grupo>) repo.findByCoordinador(repoC.findById(id).get());
    }

    @Override
    public void update(Grupo grupo) {
        repo.update(grupo.getNombre(), grupo.getCodigo(), grupo.getCoordinador(), grupo.getNumParticipantes(),
                grupo.getId());
    }

    @Override
    public Grupo findByCodigo(String codigo) {
        return repo.findByCodigo(codigo);
    }

    @Override
    public Grupo findByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public Grupo getGrupo(Integer id) {
        return repo.findById(id).get();
    }


    @Override
    public void delete(Integer id) {
        Grupo grupo = repo.findById(id).get();
        List<Participante> participantes = repoP.findByGrupo(grupo);
        for (Participante participante : participantes) {
            participante.setGrupo(null);
            participante.setEstadoCuenta(Estado.BAJA);
            repoP.save(participante);
        }
        grupo.setParticipantes(null);
        repo.save(grupo);
        repo.delete(grupo);
    }

    @Override
    public void save(Grupo grupo) {
        repo.save(grupo);
    }

    @Override
    public void updateParticipantes(Integer idGrupo, Integer numParticipantes) {
        repo.update(idGrupo, numParticipantes);
    }

    @Override
    public List<Grupo> getGrupos() {
        return repo.findAll();
    }

    @Override
    public Page<Grupo> paginas(Pageable pageable, Coordinador coordinador) {
        return (Page<Grupo>) repo.findByIdCoordinador(pageable, coordinador);
    }

}
