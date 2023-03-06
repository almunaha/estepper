package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

public interface MaterialService {
    public List<Materiales> materiales(Integer id);
    public List<Materiales> materialesGrupo(Grupo grupo);
    public void eliminarMaterial(Integer id);
    public void eliminarMaterialGrupo(Integer id);
    public void updateMaterial(Materiales material);
    public Materiales getMaterial(Integer id);
    public void deleteByParticipante(Participante p);
    public void deleteByGrupo(Grupo g);
}
