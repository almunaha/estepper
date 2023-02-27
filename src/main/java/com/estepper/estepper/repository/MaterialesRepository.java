package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialesRepository extends JpaRepository<Materiales, Integer>{
    List<Materiales> findByParticipante(Participante participante);
    List<Materiales> findByGrupo(Grupo grupo);
    void deleteByParticipanteAndId(Participante participante, Integer id);
    void deleteByGrupoAndId(Grupo grupo, Integer id);
    
}
