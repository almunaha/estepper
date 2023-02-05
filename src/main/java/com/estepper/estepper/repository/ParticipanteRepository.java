package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Participante;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List <Participante> findAll();

}
