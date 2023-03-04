package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.estepper.estepper.model.entity.Grupo;

import jakarta.transaction.Transactional;


public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
        
    Grupo findByNombre(String nombre); //select * from grupo where nombre = g.nombre 
    Grupo findByCodigo(String codigo); 
    List<Grupo> findByIdCoordinador(Integer idCoordinador);
    
    @Modifying
    @Transactional
    @Query("update Grupo SET numParticipantes = :numParticipantes WHERE id = :idGrupo")
    void update(Integer idGrupo, Integer numParticipantes);
     
}

