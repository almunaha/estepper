package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Grupo;

import jakarta.transaction.Transactional;

//@Repository
//LOS repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
        
    Grupo findByNombre(String nombre); //select * from grupo where nombre = g.nombre 
    Grupo findByCodigo(String codigo); //Ver si hacerlo con el código o con el ID
    
    @Modifying
    @Transactional
    @Query("update Grupo SET numParticipantes = :numParticipantes WHERE id = :idGrupo")
    void update(Integer idGrupo, Integer numParticipantes);
     
}

