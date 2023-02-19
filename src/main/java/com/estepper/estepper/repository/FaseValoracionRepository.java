package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Participante;



public interface FaseValoracionRepository extends JpaRepository<FaseValoracion, Integer> {
        List<FaseValoracion> findByParticipante(Participante participante);

}


