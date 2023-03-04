package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.Grupo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MaterialesRepository extends JpaRepository<Materiales, Integer>{
    List<Materiales> findByParticipante(Participante participante);
    List<Materiales> findByGrupo(Grupo grupo);
    Optional<Materiales> findById(Integer id);
    void deleteByParticipanteAndId(Participante participante, Integer id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Materiales m WHERE m.grupo = :grupo AND m.link =:link")
    void deleteByGrupoAndLink(Grupo grupo, String link);
    
}
