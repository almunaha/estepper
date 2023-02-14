package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

import jakarta.transaction.Transactional;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findAll();
    Optional<Participante> findById(Integer id);
    
    @Modifying 
    @Transactional
    @Query("update Participante SET grupo = :grupo WHERE id = :idParticipante")
    void update(Integer idParticipante, Grupo grupo);

    List<Participante> findByGrupo(Grupo grupo);
    @Modifying
    @Transactional
    @Query("UPDATE Participante p SET p.edad = :edad WHERE p.id = :id")
    void update1(Integer edad, Integer id);

}
