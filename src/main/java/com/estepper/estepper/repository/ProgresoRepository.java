package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProgresoRepository extends JpaRepository<Progreso, Integer>{
    List<Progreso> findByParticipanteAndTipo(Participante participante, TipoProgreso tipo);
    
}
