package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.enums.EstadoGrupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    Grupo findByNombre(String nombre);

    Grupo findByCodigo(String codigo);

    Grupo findById(String idGrupo);

    List<Grupo> findByCoordinador(Coordinador coordinador);

    @Modifying
    @Transactional
    @Query("update Grupo SET nombre = :nombre, codigo = :codigo, coordinador = :coordinador, numParticipantes = :numParticipantes, estadoGrupo = :estado WHERE id = :idGrupo")
    void update(String nombre, String codigo, Coordinador coordinador, Integer numParticipantes, EstadoGrupo estado, Integer idGrupo);

    @Modifying
    @Transactional
    @Query("update Grupo SET numParticipantes = :numParticipantes WHERE id = :idGrupo")
    void update(Integer idGrupo, Integer numParticipantes);

    @Query("SELECT g FROM Grupo g WHERE g.coordinador = :coordinador")
    public Page<Grupo> findByIdCoordinador(Pageable pageable, Coordinador coordinador);

}
