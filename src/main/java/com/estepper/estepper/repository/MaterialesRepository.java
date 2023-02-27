package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MaterialesRepository extends JpaRepository<Materiales, Integer>{
    List<Materiales> findByParticipante(Participante participante);
    void deleteByParticipanteAndId(Participante participante, Integer id);
    
}
