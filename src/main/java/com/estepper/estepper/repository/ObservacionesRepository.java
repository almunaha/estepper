package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Observaciones;

import jakarta.transaction.Transactional;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ObservacionesRepository extends JpaRepository<Observaciones, Integer>{

    List<Observaciones> findByIdCoordinador(Integer idCoordinador);
    List<Observaciones> findByIdGrupo(Integer idGrupo);
    Optional<Observaciones> findById(Integer idObservacion);

    //List<Observaciones> findByIdCoordinadoryIdGrupo(Integer idCoordinador, Integer idGrupo);



    @Modifying
    @Transactional
    @Query("DELETE FROM Observaciones o WHERE o.idGrupo = :g")
    void deleteAllByGrupo(Grupo g);



}





