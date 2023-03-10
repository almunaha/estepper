package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.repository.MaterialesRepository;
import com.estepper.estepper.repository.ParticipanteRepository;


@Service
public class MaterialServiceImpl implements MaterialService {
    
    @Autowired
    private MaterialesRepository repoM;

    @Autowired
    private ParticipanteRepository repoP;

    public Materiales getMaterial(Integer id){
        return repoM.findById(id).get();
    }

    @Override
    public List<Materiales> materialesGrupo(Grupo grupo){
        List<Materiales> todos = repoM.findByGrupo(grupo);
        for(int i = todos.size()-1; i > 0 ; i--){
            if(todos.get(i-1).getLink().equals(todos.get(i).getLink()) ){
                todos.remove(i);
            }
        }
        return todos;
    }

    @Override
    public void eliminarMaterial(Integer id){
        repoM.deleteById(id);
    }

    @Override
    public void eliminarMaterialGrupo(Integer id){
        Materiales material = repoM.findById(id).get();
        repoM.deleteByGrupoAndLink(material.getGrupo(), material.getLink());
    }

    @Override
    public void updateMaterial(Materiales material) {
        repoM.save(material);
    }

    @Override
    public List<Materiales> materiales(Integer id){
        return repoM.findByParticipante(repoP.findById(id).get());
    }

    @Override
    public void deleteByParticipante(Participante p) {
        repoM.deleteAllByParticipante(p);
    }

    @Override
    public void deleteByGrupo(Grupo g) {
        repoM.deleteAllByGrupo(g);
    }
}
